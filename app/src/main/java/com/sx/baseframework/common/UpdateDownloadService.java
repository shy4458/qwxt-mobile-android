package com.sx.baseframework.common;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.sx.baseframework.util.AppUtils;
import com.sx.baseframework.util.FileUtils;
import com.sx.baseframework.util.IOUtils;
import com.sx.qwxt.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Background update application service.
 * <p>Use:
 * <code>
 *     UpdateDownloadService.newBuilder(MainActivity.this)
 *         .setTitle(title)
 *         .setSavePath(dirFilePath)
 *         .setFileName(apkFileName)
 *         .setImage(notificationIcon)
 *         .setDownloadUrl(url)
 *         .start();
 * </code>
 * @author JadynZhang
 * @version 1.0.0
 */
public class UpdateDownloadService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static final String EXTRA_NAME_CONFIG = "config";
    private static final int STATE_DOWNLOADING = 1;
    private static final int STATE_DOWNLOADED = 0;
    private static final int ID_NOTIFICATION = 0;
    private Config config;

    private NotificationManager notificationManager;
    private Notification notification;
    private File apkFile;
    private static boolean isDownload = false;

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

    public static boolean isDownload() {
        return isDownload;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isDownload = true;
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            RemoteViews views = notification.contentView;
            switch (msg.what) {
                case STATE_DOWNLOADED:
                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                    views.setTextViewText(R.id.down_notification_title, "下载完成，点击安装(" + 100
                            + "%" + ")");
                    views.setProgressBar(R.id.down_notification_progress, 100, 100,
                            false);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(apkFile),
                            "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    notification.contentIntent = PendingIntent.getActivity(
                            UpdateDownloadService.this, 0, intent, 0
                    );
                    notificationManager.notify(ID_NOTIFICATION, notification);
                    AppUtils.installApk(UpdateDownloadService.this, apkFile);
                    break;
                case STATE_DOWNLOADING:
                    int rate = msg.arg1;
                    if (rate < 100) {
                        views.setTextViewText(R.id.down_notification_title, config.title + "(" + rate
                                + "%" + ")");
                        views.setProgressBar(R.id.down_notification_progress, 100, rate,
                                false);
                    }
                    notificationManager.notify(ID_NOTIFICATION, notification);
                    break;
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        config = intent.getParcelableExtra(EXTRA_NAME_CONFIG);
        File savePath = new File(config.saveFilePath);
        if(!savePath.exists()) {
            savePath.mkdirs();
        }
        apkFile = new File(savePath, config.fileName);
        showNotification();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    downloadFile(apkFile);
                } catch (Exception ignore) {}
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotification() {
        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.layout_down_notification_view);
        contentView.setImageViewResource(R.id.down_notification_image, config.imageSrcId);
        contentView.setTextViewText(R.id.down_notification_title, config.title);
        notification = new NotificationCompat.Builder(this)
                .setTicker("开始下载")
                .setSmallIcon(config.imageSrcId)
                .setCustomContentView(contentView)
                .setAutoCancel(false)
                .setWhen(System.currentTimeMillis())
                .build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notificationManager.notify(ID_NOTIFICATION, notification);
    }

    private void downloadFile(File file) throws Exception {
        long totalSize;
        int updateTotalSize = 0;
        HttpURLConnection conn = null;
        InputStream in = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL(config.donloadUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(20000);
            totalSize = conn.getContentLength();
            if(conn.getResponseCode() == 404) {
                throw new Exception("fail!");
            }
            in = conn.getInputStream();
            fos = new FileOutputStream(file, false);
            byte[] buffer = new byte[2048];
            int readLen;
            while ((readLen = in.read(buffer)) != -1) {
                fos.write(buffer, 0, readLen);
                updateTotalSize += readLen;
                int rate = (int) Math.round(updateTotalSize * 1.0 / totalSize * 100);
                if(rate < 1) {
                    rate = 1;
                }
                Message msg = handler.obtainMessage();
                msg.what = STATE_DOWNLOADING;
                msg.arg1 = rate;
                handler.sendMessage(msg);
            }
            handler.sendEmptyMessage(STATE_DOWNLOADED);
            isDownload = false;
        } finally {
            IOUtils.close(fos, in);
            if(conn != null) {
                conn.disconnect();
            }
            stopSelf();
        }
    }

    public static class Builder {

        private Config config;
        private Context context;

        Builder(Context context) {
            config = new Config();
            this.context = context;
        }

        public Builder setTitle(String title) {
            config.title = title;
            return this;
        }

        public Builder setSavePath(@NonNull File savePath) {
            config.saveFilePath = savePath.getAbsolutePath();
            return this;
        }

        public Builder setFileName(String fileName) {
            config.fileName = fileName;
            return this;
        }

        public Builder setImage(int imgId) {
            config.imageSrcId = imgId;
            return this;
        }

        public Builder setDownloadUrl(String downloadUrl) {
            if(TextUtils.isEmpty(downloadUrl)) {
                throw new RuntimeException("The download url cannot be null.");
            }
            config.donloadUrl = downloadUrl;
            return this;
        }

        public void start() {
            check(config);
            Intent intent = new Intent(context, UpdateDownloadService.class);
            intent.putExtra(UpdateDownloadService.EXTRA_NAME_CONFIG, config);
            context.startService(intent);
        }

        private void check(Config config) {
            if(TextUtils.isEmpty(config.title)) {
                config.title = "正在下载中...";
            }
            if(config.saveFilePath == null) {
                config.saveFilePath = FileUtils.getDiskCacheDir(context).getAbsolutePath();
            }
            if(TextUtils.isEmpty(config.fileName)) {
                PackageInfo packageInfo = AppUtils.getPackageInfo(context);
                if(packageInfo != null) {
                    String packageName = packageInfo.packageName;
                    String[] split = packageName.split("\\.");
                    config.fileName = split[split.length - 1] + ".apk";
                } else {
                    config.fileName = "app.apk";
                }
            }
        }
    }

    private static class Config implements Parcelable {
        String title;
        String saveFilePath;
        String fileName;
        String donloadUrl;
        int imageSrcId;

        Config(){ }

        Config(Parcel in) {
            title = in.readString();
            fileName = in.readString();
            saveFilePath = in.readString();
            donloadUrl = in.readString();
            imageSrcId = in.readInt();
        }

        public static final Creator<Config> CREATOR = new Creator<Config>() {
            @Override
            public Config createFromParcel(Parcel in) {
                return new Config(in);
            }

            @Override
            public Config[] newArray(int size) {
                return new Config[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(fileName);
            dest.writeString(saveFilePath);
            dest.writeString(donloadUrl);
            dest.writeInt(imageSrcId);
        }
    }

}

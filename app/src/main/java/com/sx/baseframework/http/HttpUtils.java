package com.sx.baseframework.http;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sx.baseframework.util.SecurityUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author JadynZhang
 * @version 1.0.0
 */
public class HttpUtils {

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private static final String HEADER_APP_KEY = "Sx_App_Key";
    private static final String HEADER_ACCESS_TOKEN = "Sx_Access_Token";
    private static final String HEADER_REFRESH_TOKEN = "Sx_Refresh_Token";
    private static final String HEADER_URL_SIGN = "Sx_Url_Sign";

    private static OkHttpClient client = new OkHttpClient();
    private static String userAgent;

    static {
        userAgent = Build.BRAND + " " + Build.FINGERPRINT + " " + Build.VERSION.SDK_INT;
    }

    public static OkHttpClient getClient() {
        return client;
    }

    public static void getAsync(@NonNull String url, @NonNull Context context,
                                final OnRequestResult result) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        String sortResult = sortParams(httpUrl, null);
        String sign = sign(httpUrl, sortResult);
        Request request = newRequest(url, context, "GET", null, sign, true);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(!call.isCanceled()) {
                    call.cancel();
                }
                if(result != null) {
                    result.result(e, null);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(result != null) {
                    result.result(null, response);
                }
            }
        });
    }

    public static void postFilesAsync(@NonNull String url, @NonNull Context context,
                                      Map<String, String> params, String fileFormName, File[] files,
                                      final OnRequestResult result) {
        List<String> list = new ArrayList<>();
        if(files != null && files.length > 0) {
            for(int i = 0, len = files.length; i < len; i++) {
                list.add(fileFormName);
            }
        }
        postFilesAsync(url, context, params, list, files, result);
    }

    public static void postFilesAsync(@NonNull String url, @NonNull Context context,
                                     Map<String, String> params, List<String> fileFormNames, File[] files,
                                     final OnRequestResult result) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        String sortResult = sortParams(httpUrl, params);
        String sign = sign(httpUrl, sortResult);
        MultipartBody.Builder mbBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(params != null && params.size() > 0) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for(Map.Entry<String, String> entry : entries) {
                mbBuilder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        if(files != null && files.length > 0) {
            for(int i = 0, len = files.length; i < len; i++) {
                File file = files[i];
                String fileName = file.getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                RequestBody body = RequestBody.create(MediaType.parse("image/" + suffix), file);
                mbBuilder.addFormDataPart(fileFormNames.get(i), fileName, body);
            }
        }
        client
            .newCall(newRequest(url, context, "POST", mbBuilder.build(), sign, true))
            .enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if(!call.isCanceled()) {
                        call.cancel();
                    }
                    if(result != null) {
                        result.result(e, null);
                    }
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(result != null) {
                        result.result(null, response);
                    }
                }
            });
    }

    public static void postFormAsync(@NonNull String url, @NonNull Context context,
                            Map<String, String> params,
                            final OnRequestResult result) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        String sortResult = sortParams(httpUrl, params);
        String sign = sign(httpUrl, sortResult);
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if(params != null && params.size() > 0) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for(Map.Entry<String, String> entry : entries) {
                String value = entry.getValue();
                if(!TextUtils.isEmpty(value)) {
                    bodyBuilder.add(entry.getKey(), value);
                }
            }
        }
        client
            .newCall(newRequest(url, context, "POST", bodyBuilder.build(), sign, true))
            .enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if(!call.isCanceled()) {
                        call.cancel();
                    }
                    if(result != null) {
                        result.result(e, null);
                    }
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(result != null) {
                        result.result(null, response);
                    }
                }
            });
    }

    public static void deleteFormAsync(@NonNull String url, @NonNull Context context,
                                     Map<String, String> params,
                                     final OnRequestResult result) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        String sortResult = sortParams(httpUrl, params);
        String sign = sign(httpUrl, sortResult);
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if(params != null && params.size() > 0) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for(Map.Entry<String, String> entry : entries) {
                String value = entry.getValue();
                if(!TextUtils.isEmpty(value)) {
                    bodyBuilder.add(entry.getKey(), value);
                }
            }
        }
        client
                .newCall(newRequest(url, context, "DELETE", bodyBuilder.build(), sign, true))
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        if(!call.isCanceled()) {
                            call.cancel();
                        }
                        if(result != null) {
                            result.result(e, null);
                        }
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(result != null) {
                            result.result(null, response);
                        }
                    }
                });
    }

    /**
     * Cancle all http requests.
     * <p> You should call this method at the end of the <code>Activity</code> or
     * <code>Service</code> life cycle, prevent unnecessary requests.
     *
     * @param context Activity or service.
     */
    public static synchronized void cancleAllCall(@NonNull Context context) {
        Dispatcher dispatcher = client.dispatcher();
        List<Call> queuedCalls = dispatcher.queuedCalls();
        for(Call call : queuedCalls) {
            if(context.equals(call.request().tag())) {
                call.cancel();
            }
        }
        List<Call> runningCalls = dispatcher.runningCalls();
        for(Call call : runningCalls) {
            if(context.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /**
     * Creates OkHttp request.
     *
     * @param url     Http full url.
     * @param context <code>Activity</code> or <code>Service</code>.
     * @param method  Http request method.
     * @param body    Http
     * @param urlSign
     * @param hasAuth
     * @return
     */
    public static Request newRequest(@NonNull String url, @NonNull Context context,
            String method, RequestBody body, String urlSign, boolean hasAuth) {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .method(method, body)
                .tag(context)
                .header("User-Agent", userAgent)
                .header(HEADER_APP_KEY, ApiAuthUtils.getAppKey())
                .header(HEADER_URL_SIGN, urlSign);
        if(hasAuth) {
            builder.header(HEADER_ACCESS_TOKEN, ApiAuthUtils.getAccessToken())
                   .header(HEADER_REFRESH_TOKEN, ApiAuthUtils.getRefreshToken());
        }
        return builder.build();
    }

    private static String sortParams(HttpUrl url, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        List<String> paramNames = new ArrayList<>();
        int limit = 0;
        if(url != null) {
            int size = url.querySize();
            if(size > 0) {
                paramNames.addAll(url.queryParameterNames());
                limit = size;
            }
        }
        if(map != null) {
            paramNames.addAll(map.keySet());
        }
        Collections.sort(paramNames);
        for(int i = 0, len = paramNames.size(); i < len; i++) {
            String str = paramNames.get(i);
            sb.append(str);
            if(i < limit && url != null) {
                List<String> values = url.queryParameterValues(str);
                if(values.size() > 1) {
                    Collections.sort(values);
                    for(String value : values) {
                        sb.append(value);
                    }
                } else if(values.size() == 1) {
                    sb.append(values.get(0));
                }
            } else {
                sb.append(map.get(str));
            }
        }
        return sb.toString();
    }

    private static String sign(HttpUrl url, String str) {
        String appSecret = ApiAuthUtils.getAppSecret();
        return SecurityUtils.hmacMD5(str + url.encodedPath() + appSecret, appSecret);
    }
}

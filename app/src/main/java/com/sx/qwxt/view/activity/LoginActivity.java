package com.sx.qwxt.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.util.UIUtils;
import com.sx.qwxt.R;
import com.sx.qwxt.dagger.component.DaggerLoginComponent;
import com.sx.qwxt.dagger.module.LoginModule;
import com.sx.qwxt.presenter.LoginPresenter;

import javax.inject.Inject;

/**
 * 作者：shy
 * 时间：2017/11/23 0023
 * 描述：登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Inject
    LoginPresenter presenter;
    private Button bSignIn;
    private EditText etLoginUsername;
    private EditText etLogninPassword;
    private static final int FAIL = 110;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case FAIL:
                    String fatlMsg = (String) msg.obj;
                    UIUtils.showToast(LoginActivity.this, fatlMsg);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().in(this);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        etLoginUsername = findViewById(R.id.et_login_username);
        etLogninPassword = findViewById(R.id.et_lognin_password);
        bSignIn = findViewById(R.id.b_sign_in);
        bSignIn.setOnClickListener(this);
        etLoginUsername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });
        etLogninPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });
    }

    @Override
    public void onClick(View v) {
        String name = etLoginUsername.getText().toString();
        String passWord = etLogninPassword.getText().toString();
        if (name.length() != 0 & passWord.length() != 0) {
            UIUtils.showToast(LoginActivity.this, "正在登录中,请稍后......");
            if (isNetworkAvailable(this)){
                presenter.login(name, passWord);
            }else {
                UIUtils.showToast(this,"当前网络异常,请检查后重试...");
            }

        } else {
            UIUtils.showToast(LoginActivity.this, "请输入用户名和密码......");
        }
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 登陆成功跳转
     */
    public void access() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    /**
     * @param localizedMessage
     */

    public void fail(String localizedMessage) {
        Message msg = Message.obtain();
        msg.what = FAIL;
        msg.obj = localizedMessage;
        mHandler.sendMessage(msg);
    }

    @Override
    protected void onDestroy() {
        HttpUtils.cancleAllCall(this);
        super.onDestroy();

    }
}

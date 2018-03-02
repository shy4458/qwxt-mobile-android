package com.sx.qwxt.presenter;

import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.http.ApiAuthUtils;
import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.http.OnRequestResult;
import com.sx.qwxt.view.activity.LoginActivity;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Response;

/**
 * 作者：shy
 * 时间：2017/11/23 0023
 * 描述：
 */
public class LoginPresenter {

    private LoginActivity activity;

    public LoginPresenter(LoginActivity activity) {
        this.activity = activity;
    }

    /**
     *
     */
    public void login(final String name, String passWord) {
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", passWord);
        HttpUtils.postFormAsync(AppConfig.IP + AppConfig.Login, activity, map, new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null) {
                    if (response != null && response.isSuccessful()) {
                            Headers headers = response.networkResponse().headers();
                            String sx_access_Token = headers.get("Sx_Access_Token");
                            String sx_refresh_token = headers.get("Sx_Refresh_Token");
                            ApiAuthUtils.saveToken(sx_access_Token,sx_refresh_token);
                            activity.access();
//                            cxkqb();
                    }else {

                    }
                }else {
                    String localizedMessage = e.getLocalizedMessage();
                    activity.fail(localizedMessage);
                }
            }
        });
    }
    public void cxkqb(){
        HttpUtils.getAsync(AppConfig.IP + AppConfig.SIGNIN_SURFACE,activity, new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                //后台生成表
            }
        });
    }
}


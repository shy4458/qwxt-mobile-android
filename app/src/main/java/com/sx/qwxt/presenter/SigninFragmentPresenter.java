package com.sx.qwxt.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.http.OnRequestResult;
import com.sx.qwxt.model.signinModel.SigninGroupSize;
import com.sx.qwxt.model.signinModel.SigninLeaveReasonNumber;
import com.sx.qwxt.view.fragment.signin.SigninFragment;

import java.io.IOException;

import okhttp3.Response;

/**
 * 作者：shy
 * 时间：2017/11/15 0015
 * 描述：签到考勤的业务
 */
public class SigninFragmentPresenter {

    private SigninFragment signinFragment;

    public SigninFragmentPresenter(SigninFragment signinFragment) {
        this.signinFragment = signinFragment;
    }

    /**
     * 获取小组数量
     */
    public void getData() {

        HttpUtils.getAsync(AppConfig.IP + AppConfig.GROUP_NUMBER, signinFragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e != null){
                    int code = response.code();
                    if (code == 1){
                        try {
                            String  str = response.body().string();
                            parserData(str);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }else {

                }
            }
        });

    }

    /**
     * 请求错误提示
     *
     * @param msg
     */
    private void errorMsg(String msg) {
        signinFragment.requestError(msg);
    }

    /**
     * 解析数据
     *
     * @param str
     */
    private void parserData(String str) {

        Gson gson = new Gson();
        SigninGroupSize model = gson.fromJson(str, SigninGroupSize.class);
        int size = model.getData().getResult().size();
        signinFragment.success(size);
    }

    /**
     * 获取每个理由下的人数
     */
    public void getLeaveData() {

        HttpUtils.getAsync(AppConfig.IP + AppConfig.REASON_NUMBER, signinFragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null) {
                    if (response != null && response.isSuccessful()) {
                        Log.d("response.isSuccessful()",response.isSuccessful() + "");
                        try {
                            String  str = response.body().string();
                            Gson gson = new Gson();
                            SigninLeaveReasonNumber number = gson.fromJson(str, SigninLeaveReasonNumber.class);
                            signinFragment.leaveAllSize(number);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }else {

                }
            }
        });
    }
}

package com.sx.qwxt.presenter;

import com.google.gson.Gson;
import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.http.OnRequestResult;
import com.sx.qwxt.model.signinModel.SigninPopupModel;
import com.sx.qwxt.model.signinModel.SigninSurfaceModel;
import com.sx.qwxt.view.fragment.signin.WholeFragment;

import java.io.IOException;

import okhttp3.Response;

/**
 * 作者：shy
 * 时间：2017/11/15 0015
 * 描述：签到考勤中各小组的业务处理
 */
public class WholeFragmentPresenter {

    private WholeFragment wholeFragment;

    public WholeFragmentPresenter(WholeFragment wholeFragment) {
        this.wholeFragment = wholeFragment;
    }

    /**
     * 获取网络数据
     */
    public void getDataCxkqb(String xz) {

        String url = AppConfig.IP + AppConfig.SIGNIN_SURFACE + xz;

        HttpUtils.getAsync(url,wholeFragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null) {
                    if (response != null && response.isSuccessful()) {
                        try {
                            String  str = response.body().string();
                            parserData(str);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }else {

                    wholeFragment.requestError("网络连接失败");

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
        wholeFragment.requestError(msg);
    }

    /**
     * 解析数据
     *
     * @param str
     */
    private void parserData(String str) {
        Gson gson = new Gson();
        SigninSurfaceModel model = gson.fromJson(str, SigninSurfaceModel.class);
        wholeFragment.success(model);
    }


    /**
     * 请假请求
     * @param yourChoice  dialog选中的项
     * @param rybh   选中的人
     */
    public void askleave(int yourChoice, String rybh) {
        String url = AppConfig.IP + AppConfig.MODIFY_STATE + AppConfig.PERSONNEL_NUMBER + rybh + AppConfig.SIGNIN_STATE + yourChoice;
        HttpUtils.getAsync(url, wholeFragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null) {
                    if (response != null && response.isSuccessful()) {
                        if (response.code() == 200) {
                            wholeFragment.notifyView();
                        }
                    }
                }else {
                    String msg =response.message();
                    wholeFragment.requestError(msg);
                }
            }
        });
    }

    /**
     * popup列表数据
     */
    public void getPopupData(){
        String url = AppConfig.IP + AppConfig.POPUP_DATA ;
        HttpUtils.getAsync(url, wholeFragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response != null && response.isSuccessful()) {
                        if (response.code() == 200) {
                            try {
                                String str = response.body().string();
                                Gson gson = new Gson();
                                SigninPopupModel signinPopupModel = gson.fromJson(str, SigninPopupModel.class);
                                wholeFragment.obtainPopup(signinPopupModel);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                    } else {
                        String msg =response.message();
                        wholeFragment.requestError(msg);
                    }
                }else {
                    e.getLocalizedMessage();
                }
            }
        });

    }

    /**
     * 一级列表提交数据
     * @param string  状态标识
     * @param rybh  人员编号
     */
    public void signinPopup(String string,String rybh) {
        String url = AppConfig.IP + AppConfig.MODIFY_STATE + AppConfig.PERSONNEL_NUMBER + rybh + AppConfig.SIGNIN_STATE + string ;
        HttpUtils.getAsync(url, wholeFragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response != null && response.isSuccessful()){
                        wholeFragment.notifyView();
                    }else {
                     //错误提示
                    }
                }else {
                    e.getLocalizedMessage();
                }
            }
        });
    }
}

package com.sx.qwxt.presenter;

import com.google.gson.Gson;
import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.http.OnRequestResult;
import com.sx.qwxt.model.VhehicleModel.VhehicleFragmentModel;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleFragment;

import java.io.IOException;

import okhttp3.Response;

/**
 * 作者：shy
 * 时间：2017/12/4 0004
 * 描述：
 */
public class VehicleFragmentPresenter {

    VehicleFragment fragment;

    public VehicleFragmentPresenter(VehicleFragment fragment) {
        this.fragment = fragment;
    }

    public void getData() {
        String url = AppConfig.IP + AppConfig.CLJHXX;
        HttpUtils.getAsync(url, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response != null && response.isSuccessful()){
                        try {
                            String str = response.body().string();
                            Gson gson = new Gson();
                            VhehicleFragmentModel vhehicleFragmentModel = gson.fromJson(str, VhehicleFragmentModel.class);
                            fragment.success(vhehicleFragmentModel);

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }else {
                    //异常
                }
            }
        });
    }

    /**
     * 归还
     */
    public void submit(String carid,String recordid,String returnDomoter) {
        String url = AppConfig.IP + AppConfig.CLGH + AppConfig.RECORDID1 + recordid + AppConfig.CARID + carid + AppConfig.RETURNODOMETER + returnDomoter;
        HttpUtils.getAsync(url, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response != null && response.isSuccessful()){
                        try {
                            String str = response.body().string();
                            fragment.submitSuccess(str);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }else {
                        //请求失败
                    }
                }else {
                    //异常
                }
            }
        });
    }
}

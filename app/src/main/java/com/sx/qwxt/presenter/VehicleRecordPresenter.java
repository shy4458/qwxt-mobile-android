package com.sx.qwxt.presenter;

import com.google.gson.Gson;
import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.http.OnRequestResult;
import com.sx.qwxt.model.VhehicleModel.VehicleMsgModel;
import com.sx.qwxt.model.VhehicleModel.VehicleRecordModel;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleRecordFragment;

import java.io.IOException;

import okhttp3.Response;

/**
 * 作者：shy
 * 时间：2017/12/4 0004
 * 描述：
 */
public class VehicleRecordPresenter {
    VehicleRecordFragment fragment;
    public VehicleRecordPresenter(VehicleRecordFragment fragment) {
        this.fragment = fragment;
    }

    //查询某辆车的信息
    public void getData(String clsyjl) {
        String urlclsyjl = AppConfig.IP + AppConfig.CARMSG + clsyjl;

        HttpUtils.getAsync(urlclsyjl, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response!= null && response.isSuccessful()){
                        try {
                            String string = response.body().string();
                            Gson gson = new Gson();
                            VehicleMsgModel vehicleMsgModel = gson.fromJson(string, VehicleMsgModel.class);
                            fragment.msgSuccess(vehicleMsgModel);

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }else {
                        //失败
                    }
                }else {
                    //异常
                }
            }
        });
        String urlrecord = AppConfig.IP + AppConfig.CARRECORD + clsyjl;

        HttpUtils.getAsync(urlrecord, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response!= null && response.isSuccessful()){
                        try {
                            String string = response.body().string();
                            Gson gson = new Gson();
                            VehicleRecordModel vehicleRecordModel = gson.fromJson(string, VehicleRecordModel.class);
                            fragment.recordSuccess(vehicleRecordModel);

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }else {
                        //失败
                    }
                }else {
                    //异常
                }
            }
        });

    }
}

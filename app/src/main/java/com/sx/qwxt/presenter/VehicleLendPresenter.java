package com.sx.qwxt.presenter;

import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.base.BaseApplication;
import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.http.OnRequestResult;
import com.sx.qwxt.view.fragment.vehicleManager.VehicleLendFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * 作者：shy
 * 时间：2017/12/4 0004
 * 描述：
 */
public class VehicleLendPresenter {

    VehicleLendFragment fragment;

    public VehicleLendPresenter(VehicleLendFragment fragment) {
        this.fragment = fragment;
    }

    // 借出提交
    public void lendSubmit(String ryid, String carid, String dometer) {

        String url = AppConfig.IP + AppConfig.CRSYJL + AppConfig.RYID + ryid + AppConfig.CARID + carid + AppConfig.LENDDOMETER + dometer;
        HttpUtils.getAsync(url, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null) {
                    if (response != null && response.isSuccessful()) {
                        try {
                            String str = response.body().string();
                            fragment.lendSuccess(str);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    } else {
                        //不成功
                    }
                } else {
                    //异常
                }
            }
        });
    }

    public void lendModifySubmit(String ryid, String dometer) {
        final String recordid = BaseApplication.get("recordid", "");
        String url = AppConfig.IP + AppConfig.MODIFY + AppConfig.RYID + ryid + AppConfig.RECORDID + recordid +  AppConfig.LENDDOMETER + dometer;
        HttpUtils.getAsync(url, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null) {
                    if (response != null && response.isSuccessful()) {
                        try {
                            String str = response.body().string();
                            JSONObject jsonObject = new JSONObject(str);
                            String code = jsonObject.getString("code");
                            String msg = jsonObject.getString("msg");
                            if (code == "-1") {
                                //修改人员已经借了车
                                fragment.lendModifyNo(msg);
                            } else {
                                //修改成功的回调
                                fragment.lendModifySuccess(str);
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (JSONException e1) {


                        }

                    } else {
                        //不成功
                    }
                } else {
                    //异常
                }
            }
        });
    }
}

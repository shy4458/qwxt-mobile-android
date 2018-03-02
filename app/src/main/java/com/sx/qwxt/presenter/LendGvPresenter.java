package com.sx.qwxt.presenter;

import com.google.gson.Gson;
import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.http.OnRequestResult;
import com.sx.qwxt.model.workManagerModel.WorkManagerZgryModel;
import com.sx.qwxt.view.fragment.vehicleManager.LendGvFragment;

import java.io.IOException;

import okhttp3.Response;

/**
 * 作者：shy
 * 时间：2017/11/30 0030
 * 描述：
 */
public class LendGvPresenter {

    LendGvFragment fragment;

    public LendGvPresenter(LendGvFragment fragment) {
        this.fragment = fragment;
    }
    public void getData(String xz) {
        HttpUtils.getAsync(AppConfig.IP + AppConfig.LENDCAR_PERSONNEL + xz, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response.code() == 200 & response.isSuccessful()){
                        try {
                            String str = response.body().string();
                            Gson gson = new Gson();
                            WorkManagerZgryModel zgryModel = gson.fromJson(str, WorkManagerZgryModel.class);
                            fragment.success(zgryModel);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }else {

                    }

                }else {
                    String localizedMessage = e.getLocalizedMessage();
                }
            }
        });
    }

    public void getDataXg(String grouping_xz) {
        String str = AppConfig.IP+ AppConfig.IN_POST + grouping_xz;
        HttpUtils.getAsync(str, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response.code() == 200 & response.isSuccessful()){
                        try {
                            String str = response.body().string();
                            Gson gson = new Gson();
                            WorkManagerZgryModel zgryModel = gson.fromJson(str, WorkManagerZgryModel.class);
                            fragment.success(zgryModel);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }else {

                    }

                }else {
                    String localizedMessage = e.getLocalizedMessage();
                }
            }
        });
    }
}

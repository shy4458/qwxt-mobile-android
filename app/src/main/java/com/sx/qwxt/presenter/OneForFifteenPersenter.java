package com.sx.qwxt.presenter;

import com.google.gson.Gson;
import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.http.OnRequestResult;
import com.sx.baseframework.util.ErrorInfo;
import com.sx.qwxt.model.recordModel.ReconrdTimeModel;
import com.sx.qwxt.view.fragment.record.OneForFifteenFragment;

import java.io.IOException;

import okhttp3.Response;

/**
 * 作者：shy
 * 时间：2017/11/19 0019
 * 描述：
 */
public class OneForFifteenPersenter {
    OneForFifteenFragment fragment;
    public OneForFifteenPersenter(OneForFifteenFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * 请求数据
     */
    public void getData() {
        String url = AppConfig.IP + AppConfig.JLAP ;
        HttpUtils.getAsync(url, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response != null && response.isSuccessful()){
                        if (response.code() == 200) {
                            try {
                                String str = response.body().string();
                                Gson gson = new Gson();
                                ReconrdTimeModel recordTimeModel = gson.fromJson(str, ReconrdTimeModel.class);
                                fragment.queryMonthly(recordTimeModel);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                        }else {
                            String msg = ErrorInfo.INFO.get(response.code());
                            errorMsg(msg);
                        }
                    }

                }else {
                    e.getLocalizedMessage();
                }
            }
        });

    }
    private void errorMsg(String msg) {
        fragment.requestError(msg);
    }


}

package com.sx.qwxt.presenter;

import com.google.gson.Gson;
import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.http.OnRequestResult;
import com.sx.baseframework.util.ErrorInfo;
import com.sx.qwxt.model.workManagerModel.WorkManagerAddToModel;
import com.sx.qwxt.model.workManagerModel.WorkManagerRwdhModel;
import com.sx.qwxt.view.fragment.record.RecordFragment;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：shy
 * 时间：2017/11/21 0021
 * 描述：
 */
public class RecordPresenter {
    RecordFragment fragment;

    public RecordPresenter(RecordFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * 查询勤务安排
     */
    public void getDataRwdh() {
        String url = AppConfig.IP + AppConfig.WORK_MANAGER;
        HttpUtils.getAsync(url, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response != null && response.isSuccessful()) {
                        if (response.code() == 200) {

                            try {
                                String  str = response.body().string();
                                Gson gson = new Gson();
                                WorkManagerRwdhModel workManagerRwdhModel = gson.fromJson(str, WorkManagerRwdhModel.class);
                                fragment.obtainRwdh(workManagerRwdhModel);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                        }

                    } else {
                        //根据code值判断错误的原因
                        String msg = ErrorInfo.INFO.get(response.code());
                        errorMsg(msg);
                    }
                }else {
                    e.getLocalizedMessage();
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
        fragment.requestError(msg);
    }

    /**
     * 获取待添加任务的人员列表   http://192.168.120.161:8080/qwxt/v1/cxwaprwry?rq=20171120&xz=
     */
    public void addToData(String clickRwdh, String clickQssj, String clickJzsj, int i) {
        HttpUtils.getAsync(AppConfig.IP + AppConfig.ADD_TO + i, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null) {
                    if (response != null && response.isSuccessful()) {
                        try {
                            String str = response.body().string();
                            Gson gson = new Gson();
                            WorkManagerAddToModel addToModel = gson.fromJson(str, WorkManagerAddToModel.class);
                            fragment.showDialogData(addToModel);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                } else {

                }
            }
        });
    }

    /**
     * 提交任务
     *
     * @param listRybhAll 选择的人员集合
     * @param rwdh
     * @param qssj
     * @param jzsj
     */
    public void taskSubmission(List<String> listRybhAll, String rwdh, String qssj, String jzsj) {
        String rybhAll = "";
        for (int i = 0; i < listRybhAll.size(); i++) {
            if (i == listRybhAll.size() - 1) {
                rybhAll = rybhAll + listRybhAll.get(i);
            } else {
                rybhAll = rybhAll + listRybhAll.get(i) + ",";
            }
        }

        HttpUtils.getAsync(AppConfig.IP + AppConfig.ARRANGE_TASKS + rybhAll + AppConfig.NUMBER + rwdh + AppConfig.QSSJ  + qssj + AppConfig.JZSJ + jzsj, fragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                fragment.submittedSuccess(response.message());
            }
        });
    }

    /**
     * 获得人员信息删除 人员
     * @param rylist
     */
    public void removeToSubmitted(List<String> rylist) {
        String rybhAll = "";
        for (int i = 0; i < rylist.size(); i++) {
            if ( i == rylist.size()-1){
                rybhAll = rybhAll + rylist.get(i);
            }else {
                rybhAll = rybhAll + rylist.get(i) + ",";
            }
        }

        String url = AppConfig.IP + AppConfig.DELETE_WORK_MANAGER + rybhAll;

        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                fragment.removeSuccess(response.message());
            }
        });
    }
}

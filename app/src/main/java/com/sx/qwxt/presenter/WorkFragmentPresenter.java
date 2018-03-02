package com.sx.qwxt.presenter;

import com.google.gson.Gson;
import com.sx.baseframework.base.AppConfig;
import com.sx.baseframework.http.HttpUtils;
import com.sx.baseframework.http.OnRequestResult;
import com.sx.baseframework.util.ErrorInfo;
import com.sx.qwxt.model.workManagerModel.WorkManagerAddToModel;
import com.sx.qwxt.model.workManagerModel.WorkManagerRwdhModel;
import com.sx.qwxt.model.workManagerModel.WorkManagerZgryModel;
import com.sx.qwxt.view.fragment.workManager.WorkManagerFragment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

/**
 * 作者：shy
 * 时间：2017/11/17 0017
 * 描述：
 */
public class WorkFragmentPresenter {

    private WorkManagerFragment workManagerFragment;

    public WorkFragmentPresenter(WorkManagerFragment workManagerFragment) {
        this.workManagerFragment = workManagerFragment;
    }

    /**
     * 今日在岗的人员  小组人员   xz=
     */
    public void ObtainZgry(String xz) {
        String url = AppConfig.IP + AppConfig.IN_POST + xz;
        HttpUtils.getAsync(url, workManagerFragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response != null && response.isSuccessful()) {
                        if (response.code() == 200) {

                            try {
                                String  str = response.body().string();
                                Gson gson = new Gson();
                                WorkManagerZgryModel zgryModel = gson.fromJson(str, WorkManagerZgryModel.class);
                                workManagerFragment.obtainZgry(zgryModel);
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
     * 查询勤务安排
     */
    public void getDataRwdh() {
        String url = AppConfig.IP + AppConfig.WORK_MANAGER;
        HttpUtils.getAsync(url, workManagerFragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response != null && response.isSuccessful()) {
                        if (response.code() == 200) {

                            try {
                                String str = response.body().string();
                                Gson gson = new Gson();
                                WorkManagerRwdhModel workManagerRwdhModel = gson.fromJson(str, WorkManagerRwdhModel.class);
                                workManagerFragment.obtainRwdh(workManagerRwdhModel);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                        }

                    } else {
                        //根据code值判断错误的原因
                        String msg = ErrorInfo.INFO.get(response.code());
                        errorMsg(msg);
                    }
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
        workManagerFragment.requestError(msg);
    }

    /**
     * 获取待添加任务的人员列表   http://192.168.120.161:8080/qwxt/v1/cxwaprwry?rq=20171120&xz=
     */
    public void addToData(String clickRwdh, String clickQssj, String clickJzsj, int i) {
        HttpUtils.getAsync(AppConfig.IP + AppConfig.ADD_TO + i, workManagerFragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null) {
                    if (response != null && response.isSuccessful()) {
                        try {
                            String str = response.body().string();
                            Gson gson = new Gson();
                            WorkManagerAddToModel addToModel = gson.fromJson(str, WorkManagerAddToModel.class);
                            workManagerFragment.showDialogData(addToModel);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                } else {
                    String message = e.getMessage();
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

        HttpUtils.getAsync(AppConfig.IP + AppConfig.ARRANGE_TASKS + rybhAll + AppConfig.NUMBER + rwdh + AppConfig.QSSJ  + qssj + AppConfig.JZSJ + jzsj, workManagerFragment.getActivity(), new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                  workManagerFragment.submittedSuccess(response.message());
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

        Map<String,String> map = new HashMap<>();
        map.put("rybh",rybhAll);
        HttpUtils.deleteFormAsync(url, workManagerFragment.getActivity(),map, new OnRequestResult() {
            @Override
            public void result(Exception e, Response response) {
                if (e == null){
                    if (response != null && response.isSuccessful()){}
                    workManagerFragment.removeSuccess(response.message());
                }else {
                    e.getLocalizedMessage();
                }
            }
        });
    }
}

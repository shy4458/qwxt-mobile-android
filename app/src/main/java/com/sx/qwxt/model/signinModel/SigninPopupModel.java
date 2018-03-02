package com.sx.qwxt.model.signinModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/17 0017
 * 描述：
 */
public class SigninPopupModel {


    /**
     * code : 1
     * msg : 操作成功
     * data : [{"cd_id":"1","cd_name":"到岗"},{"cd_id":"16","cd_name":"待考勤"},{"cd_id":"2","cd_name":"迟到"},{"cd_id":"3","cd_name":"休息"},{"cd_id":"4","cd_name":"早退"},{"cd_id":"15","cd_name":"工伤"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cd_id : 1
         * cd_name : 到岗
         */

        private String cd_id;
        private String cd_name;

        public String getCd_id() {
            return cd_id;
        }

        public void setCd_id(String cd_id) {
            this.cd_id = cd_id;
        }

        public String getCd_name() {
            return cd_name;
        }

        public void setCd_name(String cd_name) {
            this.cd_name = cd_name;
        }
    }
}

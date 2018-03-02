package com.sx.qwxt.model.signinModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/14 0014
 * 描述：请假原因
 */
public class SigninLeaveReason {

    /**
     * code : 1
     * msg : 操作成功
     * data : {"success":true,"message":"操作成功","result":[{"cd_id":"1","cd_name":"到岗"},{"cd_id":"2","cd_name":"迟到"},{"cd_id":"3","cd_name":"休息"},{"cd_id":"4","cd_name":"早退"},{"cd_id":"5","cd_name":"事假"},{"cd_id":"6","cd_name":"事假半天"},{"cd_id":"7","cd_name":"病假"},{"cd_id":"8","cd_name":"病假半天"},{"cd_id":"9","cd_name":"年假"},{"cd_id":"10","cd_name":"探亲假"},{"cd_id":"11","cd_name":"婚假"},{"cd_id":"12","cd_name":"产假"},{"cd_id":"13","cd_name":"陪产假"},{"cd_id":"14","cd_name":"丧假"},{"cd_id":"15","cd_name":"工伤"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * success : true
         * message : 操作成功
         * result : [{"cd_id":"1","cd_name":"到岗"},{"cd_id":"2","cd_name":"迟到"},{"cd_id":"3","cd_name":"休息"},{"cd_id":"4","cd_name":"早退"},{"cd_id":"5","cd_name":"事假"},{"cd_id":"6","cd_name":"事假半天"},{"cd_id":"7","cd_name":"病假"},{"cd_id":"8","cd_name":"病假半天"},{"cd_id":"9","cd_name":"年假"},{"cd_id":"10","cd_name":"探亲假"},{"cd_id":"11","cd_name":"婚假"},{"cd_id":"12","cd_name":"产假"},{"cd_id":"13","cd_name":"陪产假"},{"cd_id":"14","cd_name":"丧假"},{"cd_id":"15","cd_name":"工伤"}]
         */

        private boolean success;
        private String message;
        private List<ResultBean> result;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
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
}

package com.sx.qwxt.model.signinModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/15 0015
 * 描述：
 */
public class SigninGroupSize {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"success":true,"message":"操作成功","result":[{"xz":"1"},{"xz":"2"},{"xz":"3"},{"xz":"4"}]}
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
         * result : [{"xz":"1"},{"xz":"2"},{"xz":"3"},{"xz":"4"}]
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
             * xz : 1
             */

            private String xz;

            public String getXz() {
                return xz;
            }

            public void setXz(String xz) {
                this.xz = xz;
            }
        }
    }
}

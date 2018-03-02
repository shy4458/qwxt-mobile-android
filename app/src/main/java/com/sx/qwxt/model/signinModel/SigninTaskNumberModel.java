package com.sx.qwxt.model.signinModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/14 0014
 * 描述：勤务安排编号
 */
public class SigninTaskNumberModel {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"success":true,"message":"操作成功","result":[{"rwdh":"0000","rwqssj":"0900","rwjzsj":"1130"},{"rwdh":"0000","rwqssj":"1400","rwjzsj":"1700"},{"rwdh":"7601","rwqssj":"0900","rwjzsj":"1800"},{"rwdh":"7601","rwqssj":"1800","rwjzsj":"0830"},{"rwdh":"7621","rwqssj":"0900","rwjzsj":"1200"},{"rwdh":"7621","rwqssj":"1200","rwjzsj":"1800"},{"rwdh":"7621","rwqssj":"1800","rwjzsj":"2200"},{"rwdh":"7622","rwqssj":"0900","rwjzsj":"1200"},{"rwdh":"7622","rwqssj":"1200","rwjzsj":"1800"},{"rwdh":"7622","rwqssj":"1800","rwjzsj":"2200"},{"rwdh":"7623","rwqssj":"1200","rwjzsj":"1800"},{"rwdh":"7623","rwqssj":"1800","rwjzsj":"2200"},{"rwdh":"7623","rwqssj":"0900","rwjzsj":"1200"},{"rwdh":"7626","rwqssj":"0900","rwjzsj":"1200"},{"rwdh":"7626","rwqssj":"1200","rwjzsj":"1800"},{"rwdh":"7626","rwqssj":"1800","rwjzsj":"2200"},{"rwdh":"7627","rwqssj":"0900","rwjzsj":"1200"},{"rwdh":"7627","rwqssj":"1200","rwjzsj":"1800"},{"rwdh":"7627","rwqssj":"1800","rwjzsj":"2200"},{"rwdh":"7691","rwqssj":"0900","rwjzsj":"2100"},{"rwdh":"7691","rwqssj":"2100","rwjzsj":"0900"}]}
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
         * result : [{"rwdh":"0000","rwqssj":"0900","rwjzsj":"1130"},{"rwdh":"0000","rwqssj":"1400","rwjzsj":"1700"},{"rwdh":"7601","rwqssj":"0900","rwjzsj":"1800"},{"rwdh":"7601","rwqssj":"1800","rwjzsj":"0830"},{"rwdh":"7621","rwqssj":"0900","rwjzsj":"1200"},{"rwdh":"7621","rwqssj":"1200","rwjzsj":"1800"},{"rwdh":"7621","rwqssj":"1800","rwjzsj":"2200"},{"rwdh":"7622","rwqssj":"0900","rwjzsj":"1200"},{"rwdh":"7622","rwqssj":"1200","rwjzsj":"1800"},{"rwdh":"7622","rwqssj":"1800","rwjzsj":"2200"},{"rwdh":"7623","rwqssj":"1200","rwjzsj":"1800"},{"rwdh":"7623","rwqssj":"1800","rwjzsj":"2200"},{"rwdh":"7623","rwqssj":"0900","rwjzsj":"1200"},{"rwdh":"7626","rwqssj":"0900","rwjzsj":"1200"},{"rwdh":"7626","rwqssj":"1200","rwjzsj":"1800"},{"rwdh":"7626","rwqssj":"1800","rwjzsj":"2200"},{"rwdh":"7627","rwqssj":"0900","rwjzsj":"1200"},{"rwdh":"7627","rwqssj":"1200","rwjzsj":"1800"},{"rwdh":"7627","rwqssj":"1800","rwjzsj":"2200"},{"rwdh":"7691","rwqssj":"0900","rwjzsj":"2100"},{"rwdh":"7691","rwqssj":"2100","rwjzsj":"0900"}]
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
             * rwdh : 0000
             * rwqssj : 0900
             * rwjzsj : 1130
             */

            private String rwdh;
            private String rwqssj;
            private String rwjzsj;

            public String getRwdh() {
                return rwdh;
            }

            public void setRwdh(String rwdh) {
                this.rwdh = rwdh;
            }

            public String getRwqssj() {
                return rwqssj;
            }

            public void setRwqssj(String rwqssj) {
                this.rwqssj = rwqssj;
            }

            public String getRwjzsj() {
                return rwjzsj;
            }

            public void setRwjzsj(String rwjzsj) {
                this.rwjzsj = rwjzsj;
            }
        }
    }
}

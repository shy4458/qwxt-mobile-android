package com.sx.qwxt.model.signinModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/14 0014
 * 描述：
 */
public class SigninReasonNumber {


    /**
     * code : 1
     * msg : 操作成功
     * data : [{"qdzt":"到岗","sl":7},{"qdzt":"迟到","sl":1}]
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
         * qdzt : 到岗
         * sl : 7
         */

        private String qdzt;
        private int sl;

        public String getQdzt() {
            return qdzt;
        }

        public void setQdzt(String qdzt) {
            this.qdzt = qdzt;
        }

        public int getSl() {
            return sl;
        }

        public void setSl(int sl) {
            this.sl = sl;
        }
    }
}

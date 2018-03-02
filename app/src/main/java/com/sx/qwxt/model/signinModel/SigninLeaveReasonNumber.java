package com.sx.qwxt.model.signinModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/16 0016
 * 描述：每个理由下的人数
 */
public class SigninLeaveReasonNumber {


    /**
     * code : 1
     * msg : 操作成功
     * data : [{"qdzt":"1","qdmc":"到岗","sl":6},{"qdzt":"16","qdmc":"待考勤","sl":0},{"qdzt":"2","qdmc":"迟到","sl":1},{"qdzt":"3","qdmc":"休息","sl":0},{"qdzt":"4","qdmc":"早退","sl":0},{"qdzt":"5","qdmc":"事假","sl":0},{"qdzt":"6","qdmc":"事假半天","sl":0},{"qdzt":"7","qdmc":"病假","sl":0},{"qdzt":"8","qdmc":"病假半天","sl":0},{"qdzt":"9","qdmc":"年假","sl":0},{"qdzt":"10","qdmc":"探亲假","sl":0},{"qdzt":"11","qdmc":"婚假","sl":0},{"qdzt":"12","qdmc":"产假","sl":0},{"qdzt":"13","qdmc":"陪产假","sl":0},{"qdzt":"14","qdmc":"丧假","sl":0},{"qdzt":"15","qdmc":"工伤","sl":0}]
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
         * qdzt : 1
         * qdmc : 到岗
         * sl : 6
         */

        private String qdzt;
        private String qdmc;
        private int sl;

        public String getQdzt() {
            return qdzt;
        }

        public void setQdzt(String qdzt) {
            this.qdzt = qdzt;
        }

        public String getQdmc() {
            return qdmc;
        }

        public void setQdmc(String qdmc) {
            this.qdmc = qdmc;
        }

        public int getSl() {
            return sl;
        }

        public void setSl(int sl) {
            this.sl = sl;
        }
    }
}

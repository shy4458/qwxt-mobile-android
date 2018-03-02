package com.sx.qwxt.model.workManagerModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/20 0020
 * 描述：
 */
public class WorkManagerAddToModel {

    /**
     * code : 1
     * msg : 操作成功
     * data : [{"xm":"六卡卡","rybh":"262626","zpxx":"930731007289106432.jpg","rwdh":null,"rwqssj":null,"rwjzsj":null,"sfzz":"0","qdzt":"1"},{"xm":"liuhe","rybh":"fb1b9cc9760401","zpxx":"932204175069585408.jpg","rwdh":null,"rwqssj":null,"rwjzsj":null,"sfzz":"0","qdzt":"1"},{"xm":"六卡卡","rybh":"262626","zpxx":"930731007289106432.jpg","rwdh":null,"rwqssj":null,"rwjzsj":null,"sfzz":"0","qdzt":"1"},{"xm":"liuhe","rybh":"fb1b9cc9760401","zpxx":"932204175069585408.jpg","rwdh":null,"rwqssj":null,"rwjzsj":null,"sfzz":"0","qdzt":"1"}]
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
         * xm : 六卡卡
         * rybh : 262626
         * zpxx : 930731007289106432.jpg
         * rwdh : null
         * rwqssj : null
         * rwjzsj : null
         * sfzz : 0
         * qdzt : 1
         */

        private String xm;
        private String rybh;
        private String zpxx;
        private Object rwdh;
        private Object rwqssj;
        private Object rwjzsj;
        private String sfzz;
        private String qdzt;

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getRybh() {
            return rybh;
        }

        public void setRybh(String rybh) {
            this.rybh = rybh;
        }

        public String getZpxx() {
            return zpxx;
        }

        public void setZpxx(String zpxx) {
            this.zpxx = zpxx;
        }

        public Object getRwdh() {
            return rwdh;
        }

        public void setRwdh(Object rwdh) {
            this.rwdh = rwdh;
        }

        public Object getRwqssj() {
            return rwqssj;
        }

        public void setRwqssj(Object rwqssj) {
            this.rwqssj = rwqssj;
        }

        public Object getRwjzsj() {
            return rwjzsj;
        }

        public void setRwjzsj(Object rwjzsj) {
            this.rwjzsj = rwjzsj;
        }

        public String getSfzz() {
            return sfzz;
        }

        public void setSfzz(String sfzz) {
            this.sfzz = sfzz;
        }

        public String getQdzt() {
            return qdzt;
        }

        public void setQdzt(String qdzt) {
            this.qdzt = qdzt;
        }
    }
}

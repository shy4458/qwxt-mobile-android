package com.sx.qwxt.model.signinModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/14 0014
 * 描述：考勤表
 */
public class SigninSurfaceModel {


    /**
     * code : 1
     * msg : 操作成功
     * data : [{"xm":"宋伟超","rybh":"123459","zpxx":"930731007289106432.jpg","rwdh":"7601","rwqssj":"0900","rwjzsj":"1800","sfzz":"0","qdzt":""},{"xm":"王五","rybh":"123458","zpxx":"930731007289106432.jpg","rwdh":"7601","rwqssj":"1800","rwjzsj":"0830","sfzz":"0","qdzt":"2"},{"xm":"李四","rybh":"123457","zpxx":"930731007289106432.jpg","rwdh":"7601","rwqssj":"0900","rwjzsj":"1800","sfzz":"0","qdzt":"1"},{"xm":" 张三","rybh":"123456","zpxx":"930731007289106432.jpg","rwdh":null,"rwqssj":null,"rwjzsj":null,"sfzz":"0","qdzt":"1"},{"xm":"王卡卡","rybh":"123546","zpxx":"930731007289106432.jpg","rwdh":null,"rwqssj":null,"rwjzsj":null,"sfzz":"0","qdzt":"2"},{"xm":"六卡卡","rybh":"262626","zpxx":"930731007289106432.jpg","rwdh":null,"rwqssj":null,"rwjzsj":null,"sfzz":"0","qdzt":"9"},{"xm":"李卡卡","rybh":"454545","zpxx":"930731007289106432.jpg","rwdh":null,"rwqssj":null,"rwjzsj":null,"sfzz":"0","qdzt":"1"},{"xm":"张卡卡","rybh":"455668","zpxx":"930731007289106432.jpg","rwdh":null,"rwqssj":null,"rwjzsj":null,"sfzz":"0","qdzt":"2"}]
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
         * xm : 宋伟超
         * rybh : 123459
         * zpxx : 930731007289106432.jpg
         * rwdh : 7601
         * rwqssj : 0900
         * rwjzsj : 1800
         * sfzz : 0
         * qdzt :
         */

        private String xm;
        private String rybh;
        private String zpxx;
        private String rwdh;
        private String rwqssj;
        private String rwjzsj;
        private String sfzz;
        private String qdzt;
        private String sfjc;

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
        public String getSfjc(){
            return sfjc;
        }
        public void setSfjc(String sfjc){
            this.sfjc = sfjc;
        }
    }
}

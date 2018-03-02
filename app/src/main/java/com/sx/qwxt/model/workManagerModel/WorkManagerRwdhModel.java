package com.sx.qwxt.model.workManagerModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/11/17 0017
 * 描述：勤务安排表
 */
public class WorkManagerRwdhModel {

    /**
     * code : 1
     * msg : 操作成功
     * data : [[{"rwbh":"1","rwdh":"7601","rwqssj":"0900","rwjzsj":"1800","rylist":[{"xm":" 张三","zpxx":"930731007289106432.jpg","rybh":"123456"},{"xm":"李四","zpxx":"930731007289106432.jpg","rybh":"123457"},{"xm":"王五","zpxx":"930731007289106432.jpg","rybh":"123458"},{"xm":"宋伟超","zpxx":"930731007289106432.jpg","rybh":"123459"},{"xm":"王卡卡","zpxx":"930731007289106432.jpg","rybh":"123546"},{"xm":"六卡卡","zpxx":"930731007289106432.jpg","rybh":"262626"},{"xm":"李卡卡","zpxx":"930731007289106432.jpg","rybh":"454545"},{"xm":"张卡卡","zpxx":"930731007289106432.jpg","rybh":"455668"}]},{"rwbh":"2","rwdh":"7601","rwqssj":"1800","rwjzsj":"0830","rylist":[]}],[{"rwbh":"3","rwdh":"7621","rwqssj":"0900","rwjzsj":"1200","rylist":[]},{"rwbh":"4","rwdh":"7621","rwqssj":"1200","rwjzsj":"1800","rylist":[]},{"rwbh":"5","rwdh":"7621","rwqssj":"1800","rwjzsj":"2200","rylist":[]}],[{"rwbh":"6","rwdh":"7622","rwqssj":"0900","rwjzsj":"1200","rylist":[]},{"rwbh":"7","rwdh":"7622","rwqssj":"1200","rwjzsj":"1800","rylist":[]},{"rwbh":"8","rwdh":"7622","rwqssj":"1800","rwjzsj":"2200","rylist":[]}],[{"rwbh":"10","rwdh":"7623","rwqssj":"1200","rwjzsj":"1800","rylist":[]},{"rwbh":"11","rwdh":"7623","rwqssj":"1800","rwjzsj":"2200","rylist":[]},{"rwbh":"9","rwdh":"7623","rwqssj":"0900","rwjzsj":"1200","rylist":[]}],[{"rwbh":"12","rwdh":"7626","rwqssj":"0900","rwjzsj":"1200","rylist":[]},{"rwbh":"13","rwdh":"7626","rwqssj":"1200","rwjzsj":"1800","rylist":[]},{"rwbh":"14","rwdh":"7626","rwqssj":"1800","rwjzsj":"2200","rylist":[]}],[{"rwbh":"15","rwdh":"7627","rwqssj":"0900","rwjzsj":"1200","rylist":[]},{"rwbh":"16","rwdh":"7627","rwqssj":"1200","rwjzsj":"1800","rylist":[]},{"rwbh":"17","rwdh":"7627","rwqssj":"1800","rwjzsj":"2200","rylist":[]}],[{"rwbh":"18","rwdh":"7691","rwqssj":"0900","rwjzsj":"2100","rylist":[]},{"rwbh":"19","rwdh":"7691","rwqssj":"2100","rwjzsj":"0900","rylist":[]}],[{"rwbh":"20","rwdh":"9999","rwqssj":"0900","rwjzsj":"1130","rylist":[]},{"rwbh":"21","rwdh":"9999","rwqssj":"1400","rwjzsj":"1700","rylist":[]}]]
     */

    private int code;
    private String msg;
    private List<List<DataBean>> data;

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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rwbh : 1
         * rwdh : 7601
         * rwqssj : 0900
         * rwjzsj : 1800
         * rylist : [{"xm":" 张三","zpxx":"930731007289106432.jpg","rybh":"123456"},{"xm":"李四","zpxx":"930731007289106432.jpg","rybh":"123457"},{"xm":"王五","zpxx":"930731007289106432.jpg","rybh":"123458"},{"xm":"宋伟超","zpxx":"930731007289106432.jpg","rybh":"123459"},{"xm":"王卡卡","zpxx":"930731007289106432.jpg","rybh":"123546"},{"xm":"六卡卡","zpxx":"930731007289106432.jpg","rybh":"262626"},{"xm":"李卡卡","zpxx":"930731007289106432.jpg","rybh":"454545"},{"xm":"张卡卡","zpxx":"930731007289106432.jpg","rybh":"455668"}]
         */

        private String rwbh;
        private String rwdh;
        private String rwqssj;
        private String rwjzsj;
        private List<RylistBean> rylist;

        public String getRwbh() {
            return rwbh;
        }

        public void setRwbh(String rwbh) {
            this.rwbh = rwbh;
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

        public List<RylistBean> getRylist() {
            return rylist;
        }

        public void setRylist(List<RylistBean> rylist) {
            this.rylist = rylist;
        }

        public static class RylistBean {
            /**
             * xm :  张三
             * zpxx : 930731007289106432.jpg
             * rybh : 123456
             */

            private String xm;
            private String zpxx;
            private String rybh;

            public String getXm() {
                return xm;
            }

            public void setXm(String xm) {
                this.xm = xm;
            }

            public String getZpxx() {
                return zpxx;
            }

            public void setZpxx(String zpxx) {
                this.zpxx = zpxx;
            }

            public String getRybh() {
                return rybh;
            }

            public void setRybh(String rybh) {
                this.rybh = rybh;
            }
        }
    }
}

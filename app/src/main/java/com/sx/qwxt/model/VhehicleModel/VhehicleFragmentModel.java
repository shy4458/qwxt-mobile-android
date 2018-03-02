package com.sx.qwxt.model.VhehicleModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/12/4 0004
 * 描述：
 */
public class VhehicleFragmentModel {

    /**
     * code : 1
     * msg : 操作成功
     * data : [{"carid":"1","platenumber":"京A88888888","ryid":"fb1c6375dd8901","xm":"二二","loantime":"201712161744","loanodometer":"201801011747","returnodometer":null,"returntime":null,"recordid":"fb1cd46b059f00","state":"0"},{"carid":"2","platenumber":"京A66666666","ryid":"fb1bf3a2732601","xm":"大防守打法","loantime":"201712261010","loanodometer":"201801021010","returnodometer":null,"returntime":"20171226153432","recordid":"fb1c9a8b5ab200","state":"1"},{"carid":"3","platenumber":"京A11111111","ryid":"fb1c6378b56f00","xm":"六六","loantime":"20180103104744","loanodometer":"100","returnodometer":"60","returntime":"20180103104930","recordid":"fb211aa7431e00","state":"1"},{"carid":"4","platenumber":"京A22222222","ryid":"fb1c6384206900","xm":"十三","loantime":"201712261010","loanodometer":"201801021010","returnodometer":null,"returntime":"20171226154908","recordid":"fb1c9a8be10d00","state":"1"},{"carid":"5","platenumber":"京A33333333","ryid":"fb1c6397880400","xm":"七四","loantime":"201712261010","loanodometer":"201801021010","returnodometer":null,"returntime":"20171226154911","recordid":"fb1c9a8befc500","state":"1"},{"carid":"6","platenumber":"京A55555555","ryid":"fb1c639f35c900","xm":"九八","loantime":"201712261010","loanodometer":"201801021010","returnodometer":null,"returntime":"20171226154914","recordid":"fb1c9a8c0e4700","state":"1"},{"carid":"7","platenumber":"京A11111110","ryid":"fb1c6383736e00","xm":"十二","loantime":"201712261010","loanodometer":"201801021010","returnodometer":null,"returntime":"20171226154916","recordid":"fb1c9a8c225700","state":"1"},{"carid":"8","platenumber":"京A77777777","ryid":"fb1c6395c2f100","xm":"七七","loantime":"201712261010","loanodometer":"201801021010","returnodometer":null,"returntime":"20171226154919","recordid":"fb1c9a8c344600","state":"1"},{"carid":"9","platenumber":"京A99999999","ryid":"fb1c6378b56f00","xm":"六六","loantime":"201712261010","loanodometer":"201801021010","returnodometer":null,"returntime":"20171219132302","recordid":"fb1c9a8c649000","state":"1"},{"carid":"fb1c5bdadd3500","platenumber":"京F88888888","ryid":null,"xm":null,"loantime":null,"loanodometer":null,"returnodometer":null,"returntime":null,"recordid":null,"state":"1"}]
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
         * carid : 1
         * platenumber : 京A88888888
         * ryid : fb1c6375dd8901
         * xm : 二二
         * loantime : 201712161744
         * loanodometer : 201801011747
         * returnodometer : null
         * returntime : null
         * recordid : fb1cd46b059f00
         * state : 0
         */

        private String carid;
        private String platenumber;
        private String ryid;
        private String xm;
        private String loantime;
        private String loanodometer;
        private String returnodometer;
        private String returntime;
        private String recordid;
        private String state;

        public String getCarid() {
            return carid;
        }

        public void setCarid(String carid) {
            this.carid = carid;
        }

        public String getPlatenumber() {
            return platenumber;
        }

        public void setPlatenumber(String platenumber) {
            this.platenumber = platenumber;
        }

        public String getRyid() {
            return ryid;
        }

        public void setRyid(String ryid) {
            this.ryid = ryid;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getLoantime() {
            return loantime;
        }

        public void setLoantime(String loantime) {
            this.loantime = loantime;
        }

        public String getLoanodometer() {
            return loanodometer;
        }

        public void setLoanodometer(String loanodometer) {
            this.loanodometer = loanodometer;
        }

        public String getReturnodometer() {
            return returnodometer;
        }

        public void setReturnodometer(String returnodometer) {
            this.returnodometer = returnodometer;
        }

        public String getReturntime() {
            return returntime;
        }

        public void setReturntime(String returntime) {
            this.returntime = returntime;
        }

        public String getRecordid() {
            return recordid;
        }

        public void setRecordid(String recordid) {
            this.recordid = recordid;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}

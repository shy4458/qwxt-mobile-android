package com.sx.qwxt.model.VhehicleModel;

import java.util.List;

/**
 * 作者：shy
 * 时间：2017/12/6 0006
 * 描述：
 */
public class VehicleRecordModel {

    /**
     * code : 1
     * msg : 操作成功
     * data : [{"carid":"1","platenumber":"京A88888888","ryid":"fb1c6381d6bc00","xm":"十","loantime":"20180103140200","loanodometer":"","returnodometer":"90","returntime":"20180103140349","recordid":"","state":""},{"carid":"1","platenumber":"京A88888888","ryid":"fb1c5c68e7b300","xm":"士大夫","loantime":"20180103133929","loanodometer":"25","returnodometer":"333","returntime":"20180103134115","recordid":"","state":""},{"carid":"1","platenumber":"京A88888888","ryid":"fb1c63774d9300","xm":"五五","loantime":"20180103133353","loanodometer":"665","returnodometer":"","returntime":"20180103133633","recordid":"","state":""},{"carid":"1","platenumber":"京A88888888","ryid":"fb1c63774d9300","xm":"五五","loantime":"20180103132653","loanodometer":"666","returnodometer":"","returntime":"20180103133122","recordid":"","state":""},{"carid":"1","platenumber":"京A88888888","ryid":"fb1c6378b56f00","xm":"六六","loantime":"20180103131246","loanodometer":"","returnodometer":"","returntime":"20180103132628","recordid":"","state":""},{"carid":"1","platenumber":"京A88888888","ryid":"fb1c637795e400","xm":"四四","loantime":"20180103131045","loanodometer":"666","returnodometer":null,"returntime":"20180103131052","recordid":"","state":""},{"carid":"1","platenumber":"京A88888888","ryid":"fb1c6395c2f100","xm":"七七","loantime":"201801041550","loanodometer":"201801071550","returnodometer":null,"returntime":"","recordid":"","state":""},{"carid":"1","platenumber":"京A88888888","ryid":"fb1c6384759800","xm":"十四","loantime":"201801011550","loanodometer":"201801011550","returnodometer":null,"returntime":"","recordid":"","state":""},{"carid":"1","platenumber":"京A88888888","ryid":"fb1c6375dd8901","xm":"二二","loantime":"201712231010","loanodometer":"201712271010","returnodometer":null,"returntime":"20171226154902","recordid":"","state":""}]
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
         * ryid : fb1c6381d6bc00
         * xm : 十
         * loantime : 20180103140200
         * loanodometer :
         * returnodometer : 90
         * returntime : 20180103140349
         * recordid :
         * state :
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

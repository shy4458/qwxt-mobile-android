package com.sx.qwxt.model.VhehicleModel;

/**
 * 作者：shy
 * 时间：2017/12/6 0006
 * 描述：
 */
public class VehicleMsgModel {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"carid":"1","platenumber":"京A88888888","type":"1","typename":"紧凑车","brand":"2","brandname":"长城","buytime":"20171129","depart":"3","departname":"户籍科","insurstart":"20171101","insurstop":"20171130","upkeep":"保养了","state":"1"}
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
         * carid : 1
         * platenumber : 京A88888888
         * type : 1
         * typename : 紧凑车
         * brand : 2
         * brandname : 长城
         * buytime : 20171129
         * depart : 3
         * departname : 户籍科
         * insurstart : 20171101
         * insurstop : 20171130
         * upkeep : 保养了
         * state : 1
         */

        private String carid;
        private String platenumber;
        private String type;
        private String typename;
        private String brand;
        private String brandname;
        private String buytime;
        private String depart;
        private String departname;
        private String insurstart;
        private String insurstop;
        private String upkeep;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getBrandname() {
            return brandname;
        }

        public void setBrandname(String brandname) {
            this.brandname = brandname;
        }

        public String getBuytime() {
            return buytime;
        }

        public void setBuytime(String buytime) {
            this.buytime = buytime;
        }

        public String getDepart() {
            return depart;
        }

        public void setDepart(String depart) {
            this.depart = depart;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public String getInsurstart() {
            return insurstart;
        }

        public void setInsurstart(String insurstart) {
            this.insurstart = insurstart;
        }

        public String getInsurstop() {
            return insurstop;
        }

        public void setInsurstop(String insurstop) {
            this.insurstop = insurstop;
        }

        public String getUpkeep() {
            return upkeep;
        }

        public void setUpkeep(String upkeep) {
            this.upkeep = upkeep;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}

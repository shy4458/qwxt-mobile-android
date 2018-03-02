package com.sx.baseframework.base;

import java.util.Calendar;

public class AppConfig {

    public static final String PERF_NAME = "sx.pref";

    //ip
//    public static final String IP = "http://192.168.120.161:8080/";
    public static final String IP = "http://192.168.120.148:8080/";
    //登录
    public static final String Login = "loginMobile?";

    //修改签到状态    qwxt/v1/xgqdzt?rybh=&qdzt=
    public static final String MODIFY_STATE = "qwxt/v1/xgqdzt?";
    //人员编号
    public static final String PERSONNEL_NUMBER = "rybh=";
    //签到状态
    public static final String SIGNIN_STATE = "&qdzt=";
    //小组
    public static final String GROUP = "&xz=";
    //日期
    public static final String DATE = "rq=";

    //查询小组数量
    public static final String GROUP_NUMBER = "qwxt/v1/cxxzsl";
    //查询每个理由下的人数  +  rq = 当前时间
    public static final String REASON_NUMBER = "qwxt/v1/qdztfb?" + DATE + getDate();
    //查询考勤表  + rq    xz小组  为空是全部        : /qwxt/v1/cxkqb?rq=20171113&xz=
    public static final String SIGNIN_SURFACE = "qwxt/v1/cxkqb?" + DATE + getDate() + GROUP;
    //请假原因
    public static final String VACATION_REASON = "qwxt/v1/qjyy";

    //删除勤务安排   delete类型的请求
    public static final String DELETE_WORK_MANAGER = "qwxt/v1/deleteqwap?" + PERSONNEL_NUMBER;
    //查询今日在岗的人      qwxt/v1/cxzgry?rq=&xz=
    public static final String IN_POST = "qwxt/v1/cxzgry?" + DATE + getDate() + "&xz=";
    //查询任务代号
    public static final String TASK_NUMBER = "qwxt/v1/cxrwdh";
    //查询勤务安排
    public static final String WORK_MANAGER = "qwxt/v1/cxqwap?" + DATE + getDate();
    //给一个人 或 多个人安排任务    qwxt/v1/insertqwap?rybh=&rwdh=&rwqssj=&rwjzsj=
    public static final String ARRANGE_TASKS = "qwxt/v1/insertqwap?" + PERSONNEL_NUMBER;
    //任务代号
    public static final String NUMBER = "&rwdh=";
    //任务开始时间
    public static final String START_TASKS_DATE = "rwqssj=";
    public static final String SEND_TASKS_DATE = "rwjzsj=";
    //头像
    public static final String IM = "qwxt/v1/readImage?zpxx=";
    //pppWindows 列表数据
    public static final String POPUP_DATA = "qwxt/v1/qjyy?grade=1";
    //dialog数据
    public static final String DIALOG_DATA = "qwxt/v1/qjyy?grade=2";
    //查询本月记录
    public static final String MONTH = "qwxt/v1//cxdmkq?qssj=" + getDateMonth() + "&jzsj=" + getDate();
    //获取待添加任务的人员列表
    public static final String ADD_TO = "qwxt/v1/cxwaprwry?rq=" + getDate() + "&xz=";
    //起始时间
    public static final String QSSJ = "&rwqssj=";
    //结束时间
    public static final String JZSJ = "&rwjzsj=";
    //记录安排
    public static final String JLAP = "qwxt/v1/cxdmkqformobile?qssj=" + getDateMonth() + "&jzsj=" + getCurrentMonthLastDay();

    //车辆管理
    //借车列表的人员数据 检验过
    public static final String LENDCAR_PERSONNEL = "qwxt/v1/queryPeopleOfNotBorrowCar?" + DATE + getDate() + "&xz=";
    //车辆借还信息
    public static final String CLJHXX = "qwxt/v1/queryCarLoanReturn";
    //插入一条使用记录      qwxt/v1/insertRecord?ryid=&carid=&loanstartime=&loanstoptime=
    public static final String CRSYJL = "qwxt/v1/insertRecord?";
    //人员id
    public static final String RYID = "ryid=";
    //车辆id
    public static final String CARID = "&carid=";
    //借车开始时间
    public static final String LOANSTARTIME = "&loanstartime=";
    //借车结束时间
    public static final String LOANSTOPTIME = "&loanstoptime=";
    //里程数
    public static final String LENDDOMETER = "&loanodometer=";

    //车辆归还  qwxt/v1/updateRecordReturn?recordid=&carid=
    public static final String CLGH = "qwxt/v1/updateRecordReturn?";
    //归还里程数
    public static final String RETURNODOMETER = "&returnodometer=";
    //recordid=
    public static final String RECORDID = "&recordid=";
    public static final String RECORDID1 = "recordid=";


    //修改借车信息    qwxt/v1/updateRecord?ryid=&recordid=&loanstartime=&loanstoptime=
    public static final String MODIFY = "qwxt/v1/updateRecord?";

    //查询某辆车的信息
    public static final String CARMSG = "qwxt/v1/queryOneCarInfo?carid=";
    //查询某辆车的使用记录
    public static final String CARRECORD = "qwxt/v1/queryRecordOfCar?carid=";

    public static String getDate() {
        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期

        int mWay = c.get(Calendar.DAY_OF_WEEK);// 获取当前日期的星期
        int mHour = c.get(Calendar.HOUR_OF_DAY);//时
        int mMinute = c.get(Calendar.MINUTE);//分

        String strDay = mDay + "";
        if (strDay.length() == 1) {
            strDay = "0" + strDay;
        }
        String strMonth = mMonth + "";
        if (strMonth.length() == 1) {
            strMonth = "0" + strMonth;
        }

        return mYear + "" + strMonth + "" + strDay + "";

    }

    public static String getDateMonth() {
        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期

        int mWay = c.get(Calendar.DAY_OF_WEEK);// 获取当前日期的星期
        int mHour = c.get(Calendar.HOUR_OF_DAY);//时
        int mMinute = c.get(Calendar.MINUTE);//分
        String sMonth = mMonth + "";
        if (sMonth.length() == 1) {
            sMonth = "0" + sMonth;
        }

        return mYear + "" + sMonth + "01";

    }


    //获取一个月的天数并拼接
    public static String getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        int maxDate = a.getActualMaximum(Calendar.DAY_OF_MONTH);
        int mYear = a.get(Calendar.YEAR); // 获取当前年份
        int mMonth = a.get(Calendar.MONTH) + 1;// 获取当前月份

        String sMonth = "" + mMonth;
        if (sMonth.length() == 1){
            sMonth = "0" + sMonth;
        }

        return mYear + "" + sMonth + "" + maxDate;
    }
}

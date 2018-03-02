package com.sx.baseframework.util;

import java.util.HashMap;

/**
 * 作者：shy
 * 时间：2017/11/15 0015
 * 描述：
 */
public class ErrorInfo {
    public static HashMap<String,String> INFO = new HashMap<>();
    static {
        INFO.put("0","成功");
        INFO.put("1","服务器数据异常");
    }

}

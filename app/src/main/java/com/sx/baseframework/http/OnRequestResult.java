package com.sx.baseframework.http;

import okhttp3.Response;

public interface OnRequestResult {

    /**
     *
     *
     * @param e        Exception
     * @param response OkHttp response
     * @see Response
     */
    void result(Exception e, Response response);

}

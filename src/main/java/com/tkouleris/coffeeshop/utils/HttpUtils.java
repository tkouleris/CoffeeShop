package com.tkouleris.coffeeshop.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {

    public static String getBaseUrl(HttpServletRequest request)
    {
        return String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
    }
}

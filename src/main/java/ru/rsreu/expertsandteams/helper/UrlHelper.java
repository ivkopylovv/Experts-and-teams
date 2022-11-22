package ru.rsreu.expertsandteams.helper;


import javax.servlet.http.HttpServletResponse;

import static ru.rsreu.expertsandteams.constant.GlobalOptions.URL_PREFIX;

public class UrlHelper {
    private static final String PAGE_URL_FORMAT = "/pages/%s.jsp";
    private static final String URL_WITH_PREFIX_FORMAT = "/%s/%s";
    private static final String URL_HEADER = "X-Target";

    public static String getPageUrl(String page) {
        return String.format(PAGE_URL_FORMAT, page);
    }

    public static void setUrlHeader(HttpServletResponse response, String page) {
        response.addHeader(URL_HEADER, getUrlWithPrefix(page));
    }

    public static String getUrlWithPrefix(String target) {
        return String.format(URL_WITH_PREFIX_FORMAT, URL_PREFIX, target);
    }
}

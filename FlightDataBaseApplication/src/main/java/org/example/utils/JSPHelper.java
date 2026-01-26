package org.example.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JSPHelper {
    private static final String JSP_PATH = "/WEB-INF/jsp/%s";

    public static String getPath(String fileName){
        return JSP_PATH.formatted(fileName);
    }
}

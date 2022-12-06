package ru.rsreu.expertsandteams.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ContentType {
    private static final Map<String, String> contentTypes = new HashMap<>();

    public static final String JS = "text/javascript";
    public static final String MJS = "text/javascript";
    public static final String CSS = "text/css";
    public static final String JPEG = "image/jpeg";
    public static final String PNG = "image/png";
    public static final String SVG = "image/svg+xml";
    public static final String JSON = "application/json";
    public static final String DEFAULT = "text/html";

    public static final String JS_EXT = "js";
    public static final String MJS_EXT = "mjs";
    public static final String CSS_EXT = "css";
    public static final String JPEG_EXT = "jpeg";
    public static final String PNG_EXT = "png";
    public static final String SVG_EXT = "svg";

    static {
        contentTypes.put(JS_EXT, JS);
        contentTypes.put(MJS_EXT, MJS);
        contentTypes.put(CSS_EXT, CSS);
        contentTypes.put(JPEG_EXT, JPEG);
        contentTypes.put(PNG_EXT, PNG);
        contentTypes.put(SVG_EXT, SVG);
    }

    public static Set<String> getFileTypes() {
        return contentTypes.keySet();
    }

    public static String getContentType(String fileType) {
        if (contentTypes.containsKey(fileType)) {
            return contentTypes.get(fileType);
        }

        return DEFAULT;
    }
}

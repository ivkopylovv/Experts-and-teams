package ru.rsreu.expertsandteams.constant;

import java.util.Map;
import java.util.Set;

public class ContentType {
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

    private static final Map<String, String> contentTypes = Map.ofEntries(
            Map.entry(JS_EXT, JS),
            Map.entry(MJS_EXT, MJS),
            Map.entry(CSS_EXT, CSS),
            Map.entry(JPEG_EXT, JPEG),
            Map.entry(PNG_EXT, PNG),
            Map.entry(SVG_EXT, SVG)
    );

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

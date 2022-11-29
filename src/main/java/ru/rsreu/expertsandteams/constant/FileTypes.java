package ru.rsreu.expertsandteams.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileTypes {
    private static final Map<String, String> mimeTypes = new HashMap<>();

    private static final String JS_MIME = "text/javascript";
    private static final String MJS_MIME = "text/javascript";
    private static final String CSS_MIME = "text/css";
    private static final String JPEG_MIME = "image/jpeg";
    private static final String PNG_MIME = "image/png";
    private static final String SVG_MIME = "image/svg+xml";
    public static final String DEFAULT_MIME = "text/html";

    public static final String JS = "js";
    public static final String MJS = "mjs";
    public static final String CSS = "css";
    public static final String JPEG = "jpeg";
    public static final String PNG = "png";
    public static final String SVG = "svg";


    static {
        mimeTypes.put(JS, JS_MIME);
        mimeTypes.put(MJS, MJS_MIME);
        mimeTypes.put(CSS, CSS_MIME);
        mimeTypes.put(JPEG, JPEG_MIME);
        mimeTypes.put(PNG, PNG_MIME);
        mimeTypes.put(SVG, SVG_MIME);
    }

    public static Set<String> getFileTypes() {
        return mimeTypes.keySet();
    }

    public static String getMimeType(String fileType) {
        if (mimeTypes.containsKey(fileType)) {
            return mimeTypes.get(fileType);
        }

        return DEFAULT_MIME;
    }
}

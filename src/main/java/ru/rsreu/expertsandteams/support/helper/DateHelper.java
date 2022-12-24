package ru.rsreu.expertsandteams.support.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static final String DATE_PATTERN = "HH:mm:ss";

    private DateHelper() {}

    public static String getDateAsString(Date date) {
        return new SimpleDateFormat(DATE_PATTERN).format(date);
    }
}

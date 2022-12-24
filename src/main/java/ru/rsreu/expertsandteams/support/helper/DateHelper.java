package ru.rsreu.expertsandteams.support.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static final String TIME_PATTERN = "HH:mm:ss";

    private DateHelper() {}

    public static String timeAsString(Date date) {
        return new SimpleDateFormat(TIME_PATTERN).format(date);
    }
}

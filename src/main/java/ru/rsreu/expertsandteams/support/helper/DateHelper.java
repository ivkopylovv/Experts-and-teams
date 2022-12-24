package ru.rsreu.expertsandteams.support.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static ru.rsreu.expertsandteams.constant.ErrorMessages.DATES_NOT_VALID;

public class DateHelper {
    private static final String TODAY_DATE_PATTERN = "HH:mm:ss";
    private static final String OTHERS_DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";

    private DateHelper() {
    }

    public static String getDateAsString(Date date) {
        if (isToday(date)) {
            return "Today, " + new SimpleDateFormat(TODAY_DATE_PATTERN).format(date);
        }

        return new SimpleDateFormat(OTHERS_DATE_PATTERN).format(date);
    }

    private static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }

    private static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException(DATES_NOT_VALID);
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return isSameDay(cal1, cal2);
    }

    private static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException(DATES_NOT_VALID);
        }

        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
}

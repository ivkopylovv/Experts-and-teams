package ru.rsreu.expertsandteams.helper;

import ru.rsreu.expertsandteams.data.Session;

import java.util.Date;

public class SessionHelper {
    public static boolean validate(Session session) {
        Date currentDate = new Date();
        Date sessionExpiredDate = session.getExpiredAt();

        return sessionExpiredDate.after(currentDate);
    }
}

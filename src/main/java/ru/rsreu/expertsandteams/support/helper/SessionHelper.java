package ru.rsreu.expertsandteams.support.helper;

import ru.rsreu.expertsandteams.model.entity.Session;

import java.util.Date;

public class SessionHelper {
    private SessionHelper() {
    }

    public static boolean checkValid(Session session) {
        Date currentDate = new Date();
        Date sessionExpiredDate = session.getExpiredAt();

        return sessionExpiredDate.after(currentDate);
    }
}

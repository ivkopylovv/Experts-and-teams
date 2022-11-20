package ru.rsreu.expertsandteams.helper;

import ru.rsreu.expertsandteams.data.User;

import javax.servlet.http.HttpSession;

public class UserHelper {
    public static final String USER = "user";

    private UserHelper() {}

    public static User getUserFromSession(HttpSession session) {
        if (session == null) {
            return null;
        }

        return (User) session.getAttribute(USER);
    }

    public static void setUserToSession(HttpSession session, User user) {
        session.setAttribute(USER, user);
    }
}

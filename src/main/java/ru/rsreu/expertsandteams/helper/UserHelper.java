package ru.rsreu.expertsandteams.helper;

import ru.rsreu.expertsandteams.data.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserHelper {
    private static final String USER = "user";
    private static final String USER_ID = "user_id";
    private static final int COOKIE_MAX_AGE = 60 * 60;

    public static User getUserFromSession(HttpSession session) {
        if (session == null) {
            return null;
        }

        return (User) session.getAttribute(USER);
    }

    public static void setUserToSession(HttpSession session, User user) {
        session.setAttribute(USER, user);
    }

    public static Cookie getUserCookie(User user) {
        Cookie cookie = new Cookie(USER_ID, user.getId().toString());

        cookie.setMaxAge(COOKIE_MAX_AGE);

        return cookie;
    }

    public static String getUserIdFromCookies(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(USER_ID)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}

package ru.rsreu.expertsandteams.support.helper;

import ru.rsreu.expertsandteams.model.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserHelper {
    private static final String USER_ID = "user_id";
    private static final int COOKIE_MAX_AGE = 60 * 60;

    private UserHelper() {}

    public static Optional<User> getFromRequest(HttpServletRequest request) {
        User user = (User)request.getUserPrincipal();

        return Optional.ofNullable(user);
    }

    public static Cookie createCookie(User user) {
        Cookie cookie = new Cookie(USER_ID, user.getId().toString());

        cookie.setMaxAge(COOKIE_MAX_AGE);

        return cookie;
    }

    public static Optional<Integer> getUserIdFromCookies(Cookie[] cookies) {
        if (cookies == null) {
            return Optional.empty();
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(USER_ID)) {
                String value = cookie.getValue();

                return value != null
                    ? Optional.of(Integer.parseInt(value))
                    : Optional.empty();
            }
        }

        return Optional.empty();
    }
}

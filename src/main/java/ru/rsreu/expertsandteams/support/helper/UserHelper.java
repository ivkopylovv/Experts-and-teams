package ru.rsreu.expertsandteams.support.helper;

import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.error.UnauthorizedException;

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

    public static User tryGetFromRequest(HttpServletRequest request) {
        return getFromRequest(request).orElseThrow(UnauthorizedException::new);
    }

    public static Cookie createCookie(Long userId) {
        Cookie cookie = new Cookie(USER_ID, userId.toString());

        cookie.setMaxAge(COOKIE_MAX_AGE);

        return cookie;
    }

    public static Optional<Long> getUserIdFromCookies(Cookie[] cookies) {
        if (cookies == null) {
            return Optional.empty();
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(USER_ID)) {
                String value = cookie.getValue();

                return value != null
                    ? Optional.of(Long.parseLong(value))
                    : Optional.empty();
            }
        }

        return Optional.empty();
    }
}

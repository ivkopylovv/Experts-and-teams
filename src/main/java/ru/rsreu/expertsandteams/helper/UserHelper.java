package ru.rsreu.expertsandteams.helper;

import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.enums.RoleType;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserHelper {
    private static final String USER_ID = "user_id";
    private static final int COOKIE_MAX_AGE = 60 * 60;

    public static User getFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getBoolean("is_blocked")
        );
    }

    public static String getRolesAsString(List<RoleType> roles) {
        if (roles == null) {
            return "";
        }

        return roles.stream().map(RoleType::getRole).collect(Collectors.joining(", "));
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

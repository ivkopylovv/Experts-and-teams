package ru.rsreu.expertsandteams.handler;

import ru.rsreu.expertsandteams.container.RedirectContainer;
import ru.rsreu.expertsandteams.data.Session;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.RoleDAO;
import ru.rsreu.expertsandteams.database.dao.SessionDAO;
import ru.rsreu.expertsandteams.database.dao.UserDAO;
import ru.rsreu.expertsandteams.enums.RoleType;
import ru.rsreu.expertsandteams.helper.PermissionHelper;
import ru.rsreu.expertsandteams.helper.SessionHelper;
import ru.rsreu.expertsandteams.helper.UserHelper;
import ru.rsreu.expertsandteams.mapper.RoleMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ru.rsreu.expertsandteams.constant.ErrorMessages.LOGOUT_ERROR;
import static ru.rsreu.expertsandteams.constant.ErrorMessages.PERMISSION_ERROR;
import static ru.rsreu.expertsandteams.constant.Routes.*;

public class RedirectHandler {
    private static volatile RedirectHandler instance;

    private UserDAO userDAO;
    private SessionDAO sessionDAO;
    private RoleDAO roleDAO;

    private RedirectHandler() {
    }

    public RedirectContainer handleRedirection(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userIdAsString = UserHelper.getUserIdFromCookies(cookies);

        if (userIdAsString == null) {
            return new RedirectContainer(
                    SIGNIN
            );
        }

        Long userId = Long.parseLong(userIdAsString);
        Session session = sessionDAO.findByUserId(userId);

        if (session != null) {
            boolean isSessionValid = SessionHelper.validate(session);

            if (!isSessionValid) {
                sessionDAO.deleteByUserId(userId);

                return new RedirectContainer(
                        SIGNIN
                );
            }

            String page = request.getPathInfo().substring(1);
            User user = userDAO.findById(userId);
            List<RoleType> userRoles = RoleMapper.rolesToRoleTypes(roleDAO.findByUserId(userId));

            user.setRoles(userRoles);

            if (page.equals(SIGNIN) || page.equals(SIGNUP)) {
                return new RedirectContainer(
                        PROFILE,
                        user,
                        LOGOUT_ERROR
                );
            }

            if (PermissionHelper.isSecurityPage(request)) {
                boolean hasPermission = PermissionHelper.hasPermission(request, userRoles);

                if (!hasPermission) {
                    return new RedirectContainer(
                            PROFILE,
                            user,
                            PERMISSION_ERROR
                    );
                }
            }

            return new RedirectContainer(
                    page,
                    user
            );
        }

        return new RedirectContainer(
                SIGNIN
        );
    }

    public static RedirectHandler getInstance() {
        synchronized (RedirectHandler.class) {
            if (instance == null) {
                instance = new RedirectHandler();
                DAOFactory daoFactory = DAOFactory.getInstance();

                instance.userDAO = daoFactory.getUserDAO();
                instance.sessionDAO = daoFactory.getSessionDAO();
                instance.roleDAO = daoFactory.getRoleDAO();
            }
        }

        return instance;
    }
}

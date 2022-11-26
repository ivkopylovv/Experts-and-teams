package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.Session;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.SessionDAO;
import ru.rsreu.expertsandteams.database.dao.UserDAO;
import ru.rsreu.expertsandteams.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static ru.rsreu.expertsandteams.constant.PageOptions.CONTROLS_INVALID;
import static ru.rsreu.expertsandteams.constant.Routes.PROFILE;
import static ru.rsreu.expertsandteams.constant.Routes.SIGNIN;
import static ru.rsreu.expertsandteams.constant.SessionOptions.SESSION_TIME_LIVE;

public class SigninCommand extends FrontCommand {
    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";

    private UserDAO userDAO;
    private SessionDAO sessionDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getUserDAO();
        sessionDAO = daoFactory.getSessionDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        forward(SIGNIN);
    }

    @Override
    public void send() throws ServletException, IOException {
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);

        User user = this.userDAO.findByUsername(username);

        if (user == null || user.getBlocked() || !user.getPassword().equals(password)) {
            request.setAttribute(CONTROLS_INVALID, true);

            forward(SIGNIN);
        } else {
            Cookie userCookie = UserHelper.getUserCookie(user);
            response.addCookie(userCookie);

            Session session = new Session(user.getId(), new Date(System.currentTimeMillis() + SESSION_TIME_LIVE));
            sessionDAO.save(session);

            redirect(PROFILE);
        }
    }
}

package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.SessionDAO;
import ru.rsreu.expertsandteams.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.Routes.SIGNIN;

public class LogoutCommand extends FrontCommand {
    private SessionDAO sessionDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        DAOFactory daoFactory = DAOFactory.getInstance();
        sessionDAO = daoFactory.getSessionDAO();
    }

    @Override
    public void process() throws IOException, ServletException {
        logout();
    }

    @Override
    public void send() throws IOException, ServletException {
        logout();
    }

    private void logout() throws IOException, ServletException {
        HttpSession httpSession = request.getSession();
        User user = UserHelper.getUserFromSession(httpSession);

        if (user == null) {
            forward(SIGNIN);
            return;
        }

        httpSession.invalidate();
        sessionDAO.deleteByUserId(user.getId());

        forward(SIGNIN);
    }
}

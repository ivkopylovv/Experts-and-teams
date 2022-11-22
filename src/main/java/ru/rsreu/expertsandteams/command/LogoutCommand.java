package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.SessionDAO;
import ru.rsreu.expertsandteams.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.Routes.SIGNIN;

public class LogoutCommand extends FrontCommand {
    private static final DAOFactory daoFactory = DAOFactory.getInstance();

    private SessionDAO sessionDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        sessionDAO = daoFactory.getSessionDAO();
    }

    @Override
    public void process() throws IOException {
        logout();
    }

    @Override
    public void send() throws IOException {
        logout();
    }

    private void logout() throws IOException {
        HttpSession httpSession = request.getSession();
        User user = UserHelper.getUserFromSession(httpSession);

        if (user == null) {
            redirect(SIGNIN);
            return;
        }

        httpSession.invalidate();
        sessionDAO.deleteByUserId(user.getId());

        redirect(SIGNIN);
    }
}

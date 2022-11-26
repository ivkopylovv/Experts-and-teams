package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.SessionDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        User user = (User)request.getUserPrincipal();

        sessionDAO.deleteByUserId(user.getId());

        forward(SIGNIN);
    }
}

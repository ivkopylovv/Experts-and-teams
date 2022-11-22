package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.UserDAO;
import ru.rsreu.expertsandteams.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.Routes.PROFILE;
import static ru.rsreu.expertsandteams.constant.Routes.SIGNUP;

public class SignupCommand extends FrontCommand {
    private UserDAO userDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getUserDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        // TODO
        HttpSession session = request.getSession(false);
        User user = UserHelper.getUserFromSession(session);

        if (user == null) {
            forward(SIGNUP);
        } else {
            redirect(PROFILE);
        }
    }

    @Override
    public void send() throws ServletException, IOException {
        // TODO
    }
}

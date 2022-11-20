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

public class SignupCommand extends FrontCommand {
    private static final DAOFactory daoFactory = DAOFactory.getInstance();

    private UserDAO userDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userDAO = daoFactory.getUserDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
    }

    @Override
    public void send() throws ServletException, IOException {
    }
}

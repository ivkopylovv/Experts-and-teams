package ru.rsreu.expertsandteams.command;

import netscape.javascript.JSObject;
import oracle.jdbc.driver.json.tree.JsonpArrayImpl;
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
import java.util.ArrayList;

public class SigninCommand extends FrontCommand {

    private static final DAOFactory daoFactory = DAOFactory.getInstance();

    private UserDAO userDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userDAO = daoFactory.getUserDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = UserHelper.getUserFromSession(session);

        if (user == null) {
            forward("signin");
        } else {
            redirect("profile");
        }
    }

    @Override
    public void send() throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = this.userDAO.findByUsername(username);

        if (user == null || user.getBlocked() || !user.getPassword().equals(password)) {
            request.setAttribute("controlsInvalid", true);

            forward("signin");
        } else {

        }
    }
}

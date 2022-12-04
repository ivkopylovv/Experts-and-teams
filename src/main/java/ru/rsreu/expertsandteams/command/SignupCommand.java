package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.enums.RoleType;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.FormParams.*;
import static ru.rsreu.expertsandteams.constant.Routes.*;

public class SignupCommand extends FrontCommand {
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userService = ServiceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        forward(SIGNUP);
    }

    @Override
    public void send() throws IOException {
        String name = request.getParameter(NAME_PARAM);
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String[] skills = request.getParameterValues(SKILLS_PARAM);
        RoleType role = RoleType.valueOf(
                request.getParameter(ROLE_PARAM).toUpperCase()
        );

        userService.addUser(name, username, password, skills, role);

        redirect(SIGNIN);
    }
}

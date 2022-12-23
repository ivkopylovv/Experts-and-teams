package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.api.request.AddUserRequest;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminDashboardAddUserCommand extends FrontCommand {
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userService = ServiceFactory.getUserService();
    }

    @Override
    public void send() throws ServletException, IOException {
        AddUserRequest addUserRequest = getBody(AddUserRequest.class);

        userService.addUser(addUserRequest);
    }
}

package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.helper.UserHelper;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.FormParams.PASSWORD_PARAM;
import static ru.rsreu.expertsandteams.constant.FormParams.USERNAME_PARAM;
import static ru.rsreu.expertsandteams.constant.Routes.PROFILE;
import static ru.rsreu.expertsandteams.constant.Routes.SIGNIN;

public class SigninCommand extends FrontCommand {
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userService = ServiceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        forward(SIGNIN);
    }

    @Override
    public void send() throws IOException {
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);

        User user = userService.createSession(username, password);
        Cookie userCookie = UserHelper.getUserCookie(user);

        response.addCookie(userCookie);
        redirect(PROFILE);
    }
}

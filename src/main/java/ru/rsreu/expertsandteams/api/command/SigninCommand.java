package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.api.request.SignInRequest;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Jsp;
import ru.rsreu.expertsandteams.support.helper.UserHelper;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SigninCommand extends FrontCommand {
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userService = ServiceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        forward(Jsp.SIGNIN);
    }

    @Override
    public void send() throws IOException {
//        SignInRequest signInRequest = getBody(SignInRequest.class);

//        User user = userService.createSession(signInRequest);
//        Cookie userCookie = UserHelper.createCookie(user);

//        response.addCookie(userCookie);
    }
}

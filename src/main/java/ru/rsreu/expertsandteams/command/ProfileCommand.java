package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.helper.UserHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.Routes.PROFILE;

public class ProfileCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);
        User user = UserHelper.getUserFromSession(httpSession);

        UserHelper.setUserToSession(httpSession, user);

        forward(PROFILE);
    }
}

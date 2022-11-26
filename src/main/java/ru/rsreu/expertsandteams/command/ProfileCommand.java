package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.User;

import javax.servlet.ServletException;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.Routes.PROFILE;

public class ProfileCommand extends FrontCommand {
    private static String USER_ATTR = "user";

    @Override
    public void process() throws ServletException, IOException {
        User user = (User)request.getUserPrincipal();

        request.setAttribute(USER_ATTR, user);

        forward(PROFILE);
    }
}

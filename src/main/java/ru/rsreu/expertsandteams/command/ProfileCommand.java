package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.Routes.PROFILE;

public class ProfileCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        User user = (User)request.getUserPrincipal();

        request.setAttribute("user", user);

        forward(PROFILE);
    }
}

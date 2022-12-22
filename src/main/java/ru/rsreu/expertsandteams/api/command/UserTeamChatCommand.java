package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.enums.Jsp;

import javax.servlet.ServletException;
import java.io.IOException;

public class UserTeamChatCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward(Jsp.USER_TEAM_CHAT);
    }
}

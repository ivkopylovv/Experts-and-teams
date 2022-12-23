package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.enums.Jsp;
import ru.rsreu.expertsandteams.support.helper.UserHelper;

import javax.servlet.ServletException;
import java.io.IOException;

public class TeamsCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward(Jsp.TEAMS);
    }
}

package ru.rsreu.expertsandteams.command;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        redirect("profile");
    }
}

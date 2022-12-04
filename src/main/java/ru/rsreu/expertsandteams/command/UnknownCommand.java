package ru.rsreu.expertsandteams.command;

import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.Routes.PROFILE;

public class UnknownCommand extends FrontCommand {
    @Override
    public void process() throws IOException {
        redirect(PROFILE);
    }
}

package ru.rsreu.expertsandteams.support.helper;

import ru.rsreu.expertsandteams.api.command.FrontCommand;
import ru.rsreu.expertsandteams.configuration.CommandConfig;

import javax.servlet.http.HttpServletRequest;

public class CommandHelper {
    private CommandHelper() {}

    public static FrontCommand getCommand(HttpServletRequest request) {
        String path = request.getPathInfo();

        return CommandConfig.getCommand(path);
    }
}

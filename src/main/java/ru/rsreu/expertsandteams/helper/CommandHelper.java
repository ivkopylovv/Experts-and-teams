package ru.rsreu.expertsandteams.helper;

import ru.rsreu.expertsandteams.command.FrontCommand;
import ru.rsreu.expertsandteams.command.UnknownCommand;

import javax.servlet.http.HttpServletRequest;

public class CommandHelper {
    public static String COMMAND_PATH_FORMAT = "ru.rsreu.expertsandteams.command.%sCommand";

    public static FrontCommand getCommand(HttpServletRequest request) {
        try {
            String path = request.getPathInfo();
            String str = String.format(
                    COMMAND_PATH_FORMAT,
                    StringHelper.capitalize(path.substring(1))
            );
            Class<?> type = Class.forName(str);
            return type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }
}

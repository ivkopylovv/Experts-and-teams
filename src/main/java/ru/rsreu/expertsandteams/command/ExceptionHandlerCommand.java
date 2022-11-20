package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.exception.UserNotFoundException;

import javax.servlet.ServletException;
import java.io.IOException;

public class ExceptionHandlerCommand extends FrontCommand {
    public void handleException(RuntimeException exception) throws IOException, ServletException {
        if (exception instanceof UserNotFoundException) {
            forward("signup");
        }
    }
}

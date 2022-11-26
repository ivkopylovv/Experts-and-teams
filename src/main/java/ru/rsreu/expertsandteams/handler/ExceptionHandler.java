package ru.rsreu.expertsandteams.handler;

import ru.rsreu.expertsandteams.exception.CredentialsException;
import ru.rsreu.expertsandteams.exception.RoleNotFoundException;

import javax.servlet.ServletException;
import java.io.IOException;

import static ru.rsreu.expertsandteams.command.SigninCommand.CONTROLS_INVALID_ATTR;
import static ru.rsreu.expertsandteams.constant.ErrorMessages.INTERNAL_ERROR;
import static ru.rsreu.expertsandteams.constant.PageOptions.PUSH_ERROR;
import static ru.rsreu.expertsandteams.constant.Routes.SIGNIN;
import static ru.rsreu.expertsandteams.constant.Routes.SIGNUP;

public class ExceptionHandler extends Router {
    public void handleException(Exception exception) throws ServletException, IOException {
        if (exception instanceof RoleNotFoundException) {
            request.setAttribute(PUSH_ERROR, INTERNAL_ERROR);
            forward(SIGNUP);

            return;
        }

        if (exception instanceof CredentialsException) {
            request.setAttribute(CONTROLS_INVALID_ATTR, true);
            forward(SIGNIN);

            return;
        }

        throw new RuntimeException(exception);
    }
}

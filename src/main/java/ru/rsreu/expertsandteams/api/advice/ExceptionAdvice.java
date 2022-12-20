package ru.rsreu.expertsandteams.api.advice;

import ru.rsreu.expertsandteams.api.handler.Router;
import ru.rsreu.expertsandteams.model.api.response.ErrorResponse;
import ru.rsreu.expertsandteams.model.enums.Jsp;
import ru.rsreu.expertsandteams.model.error.CredentialsException;
import ru.rsreu.expertsandteams.model.error.RoleNotFoundException;
import ru.rsreu.expertsandteams.model.error.UserAlreadyExistsException;
import ru.rsreu.expertsandteams.model.error.UserEditingException;

import javax.servlet.ServletException;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.ErrorMessages.*;
import static ru.rsreu.expertsandteams.constant.RequestAttribute.PUSH_ERROR;

// TODO: refactor
public class ExceptionAdvice extends Router {
    public void handleException(Exception exception) throws ServletException, IOException {
        if (exception instanceof RoleNotFoundException) {
            request.setAttribute(PUSH_ERROR, INTERNAL_ERROR);
            forward(Jsp.SIGNUP);

            return;
        }

        if (exception instanceof CredentialsException) {
            send(new ErrorResponse(CREDENTIALS_ERROR));

            return;
        }

        if (exception instanceof UserAlreadyExistsException) {
            request.setAttribute(PUSH_ERROR, USER_ALREADY_EXIST_ERROR);
            forward(Jsp.SIGNUP);

            return;
        }

        if (exception instanceof UserEditingException) {
            request.setAttribute(PUSH_ERROR, EDIT_USER_ERROR);
            forward(Jsp.SIGNUP);

            return;
        }

        throw new RuntimeException(exception);
    }
}

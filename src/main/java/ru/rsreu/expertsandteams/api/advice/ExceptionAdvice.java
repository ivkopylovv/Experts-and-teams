package ru.rsreu.expertsandteams.api.advice;

import ru.rsreu.expertsandteams.api.handler.Router;
import ru.rsreu.expertsandteams.model.api.response.ErrorResponse;
import ru.rsreu.expertsandteams.model.enums.Jsp;
import ru.rsreu.expertsandteams.model.enums.Route;
import ru.rsreu.expertsandteams.model.error.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.ErrorMessages.*;
import static ru.rsreu.expertsandteams.constant.RequestAttribute.PUSH_ERROR;

public class ExceptionAdvice extends Router {
    public void handleException(Exception exception) throws ServletException, IOException {
        if (exception instanceof RoleNotFoundException) {
            json(
                    new ErrorResponse(INTERNAL_ERROR),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            return;
        }

        if (exception instanceof CredentialsException) {
            json(
                new ErrorResponse(CREDENTIALS_ERROR),
                HttpServletResponse.SC_BAD_REQUEST
            );

            return;
        }

        if (exception instanceof UserAlreadyExistsException) {
            json(
                    new ErrorResponse(USER_ALREADY_EXIST_ERROR),
                    HttpServletResponse.SC_BAD_REQUEST
            );

            return;
        }

        if (exception instanceof UserEditingException) {
            request.setAttribute(PUSH_ERROR, EDIT_USER_ERROR);
            forward(Jsp.SIGNUP);

            return;
        }

        if (exception instanceof UnauthorizedException) {
            response.sendRedirect(Route.SIGNIN.getAbsolute());

            return;
        }

        if (exception instanceof LeaveTeamNoPermissionException) {
            json(
                new ErrorResponse(LEAVE_TEAM_NO_PERMISSION_ERROR),
                HttpServletResponse.SC_BAD_REQUEST
            );

            return;
        }

        throw new RuntimeException(exception);
    }
}

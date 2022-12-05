package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.rsreu.expertsandteams.constant.FormParams.USER_ID_PARAM;
import static ru.rsreu.expertsandteams.constant.RequestAttribute.USERS_ATTR;
import static ru.rsreu.expertsandteams.constant.RequestAttribute.USER_ATTR;
import static ru.rsreu.expertsandteams.constant.Routes.MODERATOR_DASHBOARD;

public class ModeratordashboardCommand extends FrontCommand {
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userService = ServiceFactory.getUserService();
    }

    @Override
    public void process() throws IOException, ServletException {
        List<User> users = userService.getAllWithoutAdmins();
        User user = (User)request.getUserPrincipal();

        request.setAttribute(USERS_ATTR, users);
        request.setAttribute(USER_ATTR, user);

        forward(MODERATOR_DASHBOARD);
    }

    @Override
    public void send() throws IOException {
        List<Long> userIds = Arrays.stream(request.getParameterValues(USER_ID_PARAM))
                .map(Long::parseLong).collect(Collectors.toList());

        userService.changeBlockStatus(userIds);

        redirect(MODERATOR_DASHBOARD);
    }
}

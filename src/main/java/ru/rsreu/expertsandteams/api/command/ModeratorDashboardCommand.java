package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.api.request.BlockUsersRequest;
import ru.rsreu.expertsandteams.model.api.response.UserResponse;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Jsp;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.UserService;
import ru.rsreu.expertsandteams.support.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.rsreu.expertsandteams.constant.RequestAttribute.USERS;

public class ModeratorDashboardCommand extends FrontCommand {
    private UserService userService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userService = ServiceFactory.getUserService();
        user = UserHelper.tryGetFromRequest(request);
    }

    @Override
    public void process() throws IOException, ServletException {
        List<UserResponse> users = userService.getModerDashboardUsers(user.getId());

        request.setAttribute(USERS, users);

        forward(Jsp.MODERATOR_DASHBOARD);
    }

    @Override
    public void send() throws IOException {
        BlockUsersRequest blockUsersRequest = getBody(BlockUsersRequest.class);

        userService.changeBlockStatus(blockUsersRequest);
    }
}

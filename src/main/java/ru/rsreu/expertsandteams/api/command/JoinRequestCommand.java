package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.api.request.JoinTeamRequest;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.TeamJoinRequestService;
import ru.rsreu.expertsandteams.support.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinRequestCommand extends FrontCommand {
    private TeamJoinRequestService teamJoinRequestService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserHelper.tryGetFromRequest(request);
        teamJoinRequestService = ServiceFactory.getTeamJoinRequestService();
    }

    @Override
    public void send() throws ServletException, IOException {
        JoinTeamRequest joinTeamRequest = getBody(JoinTeamRequest.class);

        teamJoinRequestService.createRequest(joinTeamRequest, user.getId());
    }
}

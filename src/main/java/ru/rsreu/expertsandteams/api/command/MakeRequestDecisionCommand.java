package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.api.request.JoinTeamDecisionRequest;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.TeamJoinRequestService;
import ru.rsreu.expertsandteams.support.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeRequestDecisionCommand extends FrontCommand {
    private TeamJoinRequestService teamJoinRequestService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        teamJoinRequestService = ServiceFactory.getTeamJoinRequestService();
        user = UserHelper.tryGetFromRequest(request);;
    }

    @Override
    public void send() throws ServletException, IOException {
        JoinTeamDecisionRequest joinTeamDecisionRequest = getBody(JoinTeamDecisionRequest.class);

        teamJoinRequestService.makeDecisionOnRequest(joinTeamDecisionRequest, user);
    }
}

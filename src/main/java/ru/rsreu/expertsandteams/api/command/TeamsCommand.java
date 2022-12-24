package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.api.response.TeamResponse;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Jsp;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.TeamService;
import ru.rsreu.expertsandteams.support.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.rsreu.expertsandteams.constant.RequestAttribute.TEAMS;

public class TeamsCommand extends FrontCommand {
    private TeamService teamService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserHelper.tryGetFromRequest(request);
        teamService = ServiceFactory.getTeamService();
    }

    @Override
    public void process() throws ServletException, IOException {
        List<TeamResponse> teamResponses = teamService.findUserTeams(user);

        request.setAttribute(TEAMS, teamResponses);

        forward(Jsp.TEAMS);
    }
}

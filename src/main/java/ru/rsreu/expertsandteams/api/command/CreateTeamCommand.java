package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.TeamService;
import ru.rsreu.expertsandteams.support.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.RequestParams.TEAM_NAME;

public class CreateTeamCommand extends FrontCommand {
    private TeamService teamService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserHelper.tryGetFromRequest(request);
        teamService = ServiceFactory.getTeamService();
    }

    @Override
    public void send() throws ServletException, IOException {
        String tableName = request.getParameter(TEAM_NAME);

        teamService.createTeam(tableName, user.getId());
    }
}

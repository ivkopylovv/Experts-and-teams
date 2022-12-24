package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.api.response.AvailableExpertResponse;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.rsreu.expertsandteams.constant.RequestParams.TEAM_ID;

public class TeamAvailableExpertsCommand extends FrontCommand {
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userService = ServiceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        Long teamId = Long.parseLong(request.getParameter(TEAM_ID));
        List<AvailableExpertResponse> availableExperts = userService.getAvailableExperts(teamId);

        json(availableExperts);
    }
}

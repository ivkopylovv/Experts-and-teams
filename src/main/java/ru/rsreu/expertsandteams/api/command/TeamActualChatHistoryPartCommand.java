package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.api.response.ChatResponse;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.TeamMessageService;
import ru.rsreu.expertsandteams.support.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.RequestParams.TEAM_ID;

public class TeamActualChatHistoryPartCommand extends FrontCommand {
    private TeamMessageService teamMessageService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserHelper.tryGetFromRequest(request);
        teamMessageService = ServiceFactory.getTeamMessageService();
    }

    @Override
    public void process() throws ServletException, IOException {
        Long teamId = Long.parseLong(request.getParameter(TEAM_ID));

        ChatResponse chatResponse = teamMessageService.getChatHistoryActualPart(teamId, user.getId());

        json(chatResponse.getMessages());
    }
}

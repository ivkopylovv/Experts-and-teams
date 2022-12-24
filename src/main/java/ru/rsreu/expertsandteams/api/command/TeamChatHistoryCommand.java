package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.api.request.SendMessageRequest;
import ru.rsreu.expertsandteams.model.api.response.ChatResponse;
import ru.rsreu.expertsandteams.model.api.response.MessageResponse;
import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Jsp;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.TeamMessageService;
import ru.rsreu.expertsandteams.service.TeamService;
import ru.rsreu.expertsandteams.support.helper.UserHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.RequestAttribute.*;
import static ru.rsreu.expertsandteams.constant.RequestParams.TEAM_ID;

public class TeamChatHistoryCommand extends FrontCommand {
    private TeamService teamService;
    private TeamMessageService teamMessageService;
    private User user;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        user = UserHelper.tryGetFromRequest(request);
        teamService = ServiceFactory.getTeamService();
        teamMessageService = ServiceFactory.getTeamMessageService();
    }

    @Override
    public void process() throws ServletException, IOException {
        Long teamId = Long.parseLong(request.getParameter(TEAM_ID));
        ChatResponse chatResponse = teamMessageService.getChatHistory(teamId, user.getId());
        User captain = teamService.findById(teamId).getCaptain();

        request.setAttribute(CAPTAIN, captain);
        request.setAttribute(USER, user);
        request.setAttribute(CHAT_RESPONSE, chatResponse);

        forward(Jsp.TEAM_CHAT);
    }

    @Override
    public void send() throws ServletException, IOException {
        SendMessageRequest sendMessageRequest = getBody(SendMessageRequest.class);
        MessageResponse messageResponse = teamMessageService.sendMessage(sendMessageRequest, user.getId());

        json(messageResponse);
    }
}

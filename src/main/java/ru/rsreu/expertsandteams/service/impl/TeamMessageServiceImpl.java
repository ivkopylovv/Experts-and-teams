package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.database.dao.*;
import ru.rsreu.expertsandteams.model.api.request.GetChatRequest;
import ru.rsreu.expertsandteams.model.api.request.SendMessageRequest;
import ru.rsreu.expertsandteams.model.api.response.ChatResponse;
import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.model.entity.TeamMessage;
import ru.rsreu.expertsandteams.model.error.TeamNotFoundException;
import ru.rsreu.expertsandteams.service.TeamMessageService;
import ru.rsreu.expertsandteams.support.mapper.TeamMessageMapper;

import java.util.List;

public class TeamMessageServiceImpl implements TeamMessageService {
    private static volatile TeamMessageServiceImpl instance;

    private final TeamMessageDAO teamMessageDAO;
    private final TeamDAO teamDAO;
    private final UserDAO userDAO;
    private final LastMessageRequestDAO lastMessageRequestDAO;

    public TeamMessageServiceImpl(TeamMessageDAO teamMessageDAO, TeamDAO teamDAO, UserDAO userDAO, LastMessageRequestDAO lastMessageRequestDAO) {
        this.teamMessageDAO = teamMessageDAO;
        this.teamDAO = teamDAO;
        this.userDAO = userDAO;
        this.lastMessageRequestDAO = lastMessageRequestDAO;
    }

    @Override
    public void sendMessage(SendMessageRequest request, Long userId) {
        teamMessageDAO.save(TeamMessageMapper.mapToTeamMessage(request, userId));

        if (request.getExpertId() != null) {
            teamDAO.addExpert(request.getTeamId(), request.getExpertId());
            userDAO.incrementExpertTeamsCount(request.getExpertId());
        }
    }

    @Override
    public ChatResponse getChatHistory(GetChatRequest request, Long userId) {
        lastMessageRequestDAO.upsert(request.getTeamId(), userId);

        List<TeamMessage> teamMessages = teamMessageDAO.findByTeamId(request.getTeamId());

        return getChatResponse(teamMessages, request);
    }

    @Override
    public ChatResponse getChatHistoryActualPart(GetChatRequest request, Long userId) {
        lastMessageRequestDAO.upsert(request.getTeamId(), userId);

        List<TeamMessage> teamMessages = teamMessageDAO.findActualMessagesByTeamIdAndUserId(request.getTeamId(), userId);

        return getChatResponse(teamMessages, request);
    }

    private ChatResponse getChatResponse(List<TeamMessage> teamMessages, GetChatRequest request) {
        if (teamMessages.isEmpty()) {
            Team team = teamDAO.findById(request.getTeamId())
                    .orElseThrow(TeamNotFoundException::new);

            return TeamMessageMapper.mapToChatResponse(teamMessages, team.getName());
        }

        return TeamMessageMapper.mapToChatResponse(teamMessages, teamMessages.get(0).getTeam().getName());
    }

    public static TeamMessageServiceImpl getInstance() {
        synchronized (TeamMessageServiceImpl.class) {
            if (instance == null) {
                instance = new TeamMessageServiceImpl(
                        DAOFactory.getTeamMessageDAO(),
                        DAOFactory.getTeamDAO(),
                        DAOFactory.getUserDAO(),
                        DAOFactory.getLastMessageRequestDAO()
                );
            }
        }

        return instance;
    }
}

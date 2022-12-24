package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.TeamDAO;
import ru.rsreu.expertsandteams.database.dao.TeamMessageDAO;
import ru.rsreu.expertsandteams.database.dao.*;
import ru.rsreu.expertsandteams.model.api.request.SendMessageRequest;
import ru.rsreu.expertsandteams.model.api.response.ChatResponse;
import ru.rsreu.expertsandteams.model.api.response.MessageResponse;
import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.model.entity.TeamMessage;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.error.TeamMessageNotFoundException;
import ru.rsreu.expertsandteams.model.error.TeamNotFoundException;
import ru.rsreu.expertsandteams.model.error.UserNotFoundException;
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
    public MessageResponse sendMessage(SendMessageRequest request, Long userId) {
        User expert = userDAO.findById(request.getExpertId())
                .orElseThrow(UserNotFoundException::new);
        User user = userDAO.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        teamDAO.findById(request.getTeamId())
                .orElseThrow(TeamNotFoundException::new);

        TeamMessage teamMessage = teamMessageDAO.save(TeamMessageMapper.mapToTeamMessage(request, user, expert))
                .orElseThrow(TeamMessageNotFoundException::new);

        if (request.getExpertId() != null) {
            teamDAO.addExpert(request.getTeamId(), request.getExpertId());
            userDAO.incrementExpertTeamsCount(request.getExpertId());
        }

        lastMessageRequestDAO.upsert(request.getTeamId(), userId);

        return TeamMessageMapper.mapToMessageResponse(teamMessage);
    }

    @Override
    public ChatResponse getChatHistory(Long teamId, Long userId) {
        List<TeamMessage> teamMessages = teamMessageDAO.findByTeamId(teamId);

        lastMessageRequestDAO.upsert(teamId, userId);

        return getChatResponse(teamMessages, teamId);
    }

    @Override
    public ChatResponse getChatHistoryActualPart(Long teamId, Long userId) {
        List<TeamMessage> teamMessages = teamMessageDAO.findActualMessagesByTeamIdAndUserId(teamId, userId);

        lastMessageRequestDAO.upsert(teamId, userId);

        return getChatResponse(teamMessages, teamId);
    }

    private ChatResponse getChatResponse(List<TeamMessage> teamMessages, Long teamId) {
        if (teamMessages.isEmpty()) {
            Team team = teamDAO.findById(teamId)
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

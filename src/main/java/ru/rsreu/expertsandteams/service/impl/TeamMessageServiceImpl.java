package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.TeamDAO;
import ru.rsreu.expertsandteams.database.dao.TeamMessageDAO;
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

    public TeamMessageServiceImpl(TeamMessageDAO teamMessageDAO, TeamDAO teamDAO) {
        this.teamMessageDAO = teamMessageDAO;
        this.teamDAO = teamDAO;
    }

    @Override
    public void sendMessage(SendMessageRequest request, Long userId) {
        teamMessageDAO.save(TeamMessageMapper.mapToTeamMessage(request, userId));

        if (request.getExpertId() != null) {
            teamDAO.addExpert(request.getTeamId(), request.getExpertId());
        }
    }

    @Override
    public ChatResponse getChatHistory(Long teamId) {
        List<TeamMessage> teamMessages = teamMessageDAO.findByTeamId(teamId);

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
                        DAOFactory.getTeamDAO()
                );
            }
        }

        return instance;
    }
}

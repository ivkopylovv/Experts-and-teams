package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.database.dao.*;
import ru.rsreu.expertsandteams.model.api.request.JoinTeamDecisionRequest;
import ru.rsreu.expertsandteams.model.api.request.JoinTeamRequest;
import ru.rsreu.expertsandteams.model.api.response.JoinTeamResponse;
import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.model.entity.TeamJoinRequest;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.error.TeamJoinRequestNotFoundException;
import ru.rsreu.expertsandteams.model.error.UserNotFoundException;
import ru.rsreu.expertsandteams.service.TeamJoinRequestService;
import ru.rsreu.expertsandteams.support.mapper.NotificationMapper;
import ru.rsreu.expertsandteams.support.mapper.TeamJoinRequestMapper;
import ru.rsreu.expertsandteams.support.mapper.TeamMessageMapper;

import java.util.List;
import java.util.stream.Collectors;

import static ru.rsreu.expertsandteams.constant.ActionMessage.JOIN_TEAM_MESSAGE;

public class TeamJoinRequestServiceImpl implements TeamJoinRequestService {
    private static volatile TeamJoinRequestServiceImpl instance;

    private final TeamJoinRequestDAO teamJoinRequestDAO;
    private final TeamDAO teamDAO;
    private final UserDAO userDAO;
    private final TeamMessageDAO teamMessageDAO;
    private final NotificationDAO notificationDAO;

    public TeamJoinRequestServiceImpl(
            TeamJoinRequestDAO teamJoinRequestDAO, TeamDAO teamDAO,
            UserDAO userDAO, TeamMessageDAO teamMessageDAO,
            NotificationDAO notificationDAO) {
        this.teamJoinRequestDAO = teamJoinRequestDAO;
        this.teamDAO = teamDAO;
        this.userDAO = userDAO;
        this.teamMessageDAO = teamMessageDAO;
        this.notificationDAO = notificationDAO;
    }

    @Override
    public TeamJoinRequest findById(Long id) {
        return teamJoinRequestDAO.findById(id)
                .orElseThrow(TeamJoinRequestNotFoundException::new);
    }

    @Override
    public void createRequest(JoinTeamRequest request, Long userId) {
        teamJoinRequestDAO.save(new TeamJoinRequest(
                new User(userId),
                new Team(request.getTeamId()),
                request.getMessage())
        );
    }

    @Override
    public List<JoinTeamResponse> findAllCaptainRequests(Long captainId) {
        return teamJoinRequestDAO.findByCaptainId(captainId)
                .stream()
                .map(TeamJoinRequestMapper::mapToJoinTeamResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void makeDecisionOnRequest(JoinTeamDecisionRequest request, User captain) {
        TeamJoinRequest teamJoinRequest = findById(request.getId());
        User user = userDAO.findById(teamJoinRequest.getUser().getId())
                .orElseThrow(UserNotFoundException::new);

        if (request.isAccepted()) {
            teamDAO.addTeamMember(
                    teamJoinRequest.getTeam().getId(),
                    user.getId()
            );
            teamDAO.incrementTeamMembers(teamJoinRequest.getTeam().getId());
            teamMessageDAO.save(TeamMessageMapper.mapToEmptyTeamMessage(
                    teamJoinRequest.getTeam().getId(),
                    JOIN_TEAM_MESSAGE,
                    user.getName())
            );
        }

        notificationDAO.save(NotificationMapper.mapToNotification(user, captain, request.isAccepted()));
        teamJoinRequestDAO.deleteById(request.getId());
    }

    public static TeamJoinRequestServiceImpl getInstance() {
        synchronized (TeamJoinRequestServiceImpl.class) {
            if (instance == null) {
                instance = new TeamJoinRequestServiceImpl(
                        DAOFactory.getTeamJoinRequestDAO(),
                        DAOFactory.getTeamDAO(),
                        DAOFactory.getUserDAO(),
                        DAOFactory.getTeamMessageDAO(),
                        DAOFactory.getNotificationDAO()
                );
            }
        }

        return instance;
    }
}

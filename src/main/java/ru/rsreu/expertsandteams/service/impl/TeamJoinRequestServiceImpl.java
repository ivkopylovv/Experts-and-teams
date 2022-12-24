package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.TeamDAO;
import ru.rsreu.expertsandteams.database.dao.TeamJoinRequestDAO;
import ru.rsreu.expertsandteams.model.api.request.JoinTeamDecisionRequest;
import ru.rsreu.expertsandteams.model.api.request.JoinTeamRequest;
import ru.rsreu.expertsandteams.model.api.response.JoinTeamResponse;
import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.model.entity.TeamJoinRequest;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.error.TeamJoinRequestNotFoundException;
import ru.rsreu.expertsandteams.service.TeamJoinRequestService;
import ru.rsreu.expertsandteams.support.mapper.TeamJoinRequestMapper;

import java.util.List;
import java.util.stream.Collectors;

public class TeamJoinRequestServiceImpl implements TeamJoinRequestService {
    private static volatile TeamJoinRequestServiceImpl instance;

    private final TeamJoinRequestDAO teamJoinRequestDAO;
    private final TeamDAO teamDAO;

    public TeamJoinRequestServiceImpl(TeamJoinRequestDAO teamJoinRequestDAO, TeamDAO teamDAO) {
        this.teamJoinRequestDAO = teamJoinRequestDAO;
        this.teamDAO = teamDAO;
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
    public void makeDecisionOnRequest(JoinTeamDecisionRequest request) {
        TeamJoinRequest teamJoinRequest = findById(request.getId());

        if (request.isAccepted()) {
            teamDAO.addTeamMember(
                    teamJoinRequest.getTeam().getId(),
                    teamJoinRequest.getUser().getId()
            );
            teamDAO.incrementTeamMembers(teamJoinRequest.getTeam().getId());
        }

        teamJoinRequestDAO.deleteById(request.getId());
    }

    public static TeamJoinRequestServiceImpl getInstance() {
        synchronized (TeamJoinRequestServiceImpl.class) {
            if (instance == null) {
                instance = new TeamJoinRequestServiceImpl(
                        DAOFactory.getTeamJoinRequestDAO(),
                        DAOFactory.getTeamDAO()
                );
            }
        }

        return instance;
    }
}

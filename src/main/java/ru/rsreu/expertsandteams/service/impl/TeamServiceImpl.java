package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.model.api.response.TeamResponse;
import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.TeamDAO;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.error.LeaveTeamNoPermissionException;
import ru.rsreu.expertsandteams.model.error.TeamNotFoundException;
import ru.rsreu.expertsandteams.service.TeamService;
import ru.rsreu.expertsandteams.support.mapper.TeamMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TeamServiceImpl implements TeamService {
    private static volatile TeamServiceImpl instance;

    private final TeamDAO teamDAO;

    public TeamServiceImpl(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public Team findById(Long id) {
        return teamDAO.findById(id)
                .orElseThrow(TeamNotFoundException::new);
    }

    @Override
    public List<TeamResponse> findUserTeams(Long userId) {
        return teamDAO.findByUserId(userId)
                .stream()
                .map(TeamMapper::mapToTeamResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void leaveTeam(Long teamId, Long userId) {
        Team team = findById(teamId);

        if (team.getMembersCount() > 1) {
            if (Objects.equals(team.getCaptain().getId(), userId)) {
                teamDAO.deleteById(teamId);
            } else {
                throw new LeaveTeamNoPermissionException();
            }
        } else {
            teamDAO.deleteTeamMember(teamId, userId);
            teamDAO.decrementTeamMembers(teamId);
        }
    }

    @Override
    public void createTeam(String teamName, Long captainId) {
        Team team = teamDAO.save(new Team(teamName, new User(captainId)))
                .orElseThrow(TeamNotFoundException::new);
        teamDAO.addTeamMember(team.getId(), captainId);
        teamDAO.incrementTeamMembers(team.getId());
    }

    @Override
    public List<TeamResponse> findAvailableTeams(Long userId) {
        return teamDAO.findAvailableTeams(userId)
                .stream()
                .map(TeamMapper::mapToTeamResponse)
                .collect(Collectors.toList());
    }

    public static TeamServiceImpl getInstance() {
        synchronized (TeamServiceImpl.class) {
            if (instance == null) {
                instance = new TeamServiceImpl(
                        DAOFactory.getTeamDAO()
                );
            }
        }

        return instance;
    }
}

package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.database.dao.UserDAO;
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

import static ru.rsreu.expertsandteams.model.enums.Role.*;

public class TeamServiceImpl implements TeamService {
    private static volatile TeamServiceImpl instance;

    private final TeamDAO teamDAO;
    private final UserDAO userDAO;

    public TeamServiceImpl(TeamDAO teamDAO, UserDAO userDAO) {
        this.teamDAO = teamDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Team findById(Long id) {
        return teamDAO.findById(id)
                .orElseThrow(TeamNotFoundException::new);
    }

    @Override
    public List<TeamResponse> findUserTeams(User user) {
        List<Team> teams;

        if (USER.getName().equals(user.getRole())) {
            teams = teamDAO.findByUserId(user.getId());
        } else {
            teams = teamDAO.findByExpertId(user.getId());
        }

        return teams
                .stream()
                .map(TeamMapper::mapToTeamResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void leaveTeam(Long teamId, User user) {
        Team team = findById(teamId);

        if (EXPERT.getName().equals(user.getRole())) {
            teamDAO.deleteExpert(teamId, user.getId());
            userDAO.decrementExpertTeamsCount(user.getId());
        } else {
            if (Objects.equals(team.getCaptain().getId(), user.getId())) {
                if (team.getMembersCount() > 1) {
                    throw new LeaveTeamNoPermissionException();
                }

                teamDAO.deleteById(teamId);
            } else {
                teamDAO.deleteTeamMember(teamId, user.getId());
                teamDAO.decrementTeamMembers(teamId);
            }
        }
    }

    @Override
    public void createTeam(String teamName, Long captainId) {
        Team team = teamDAO.save(new Team(teamName, new User(captainId)))
                .orElseThrow(TeamNotFoundException::new);
        teamDAO.addTeamMember(team.getId(), captainId);
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
                        DAOFactory.getTeamDAO(),
                        DAOFactory.getUserDAO()
                );
            }
        }

        return instance;
    }
}

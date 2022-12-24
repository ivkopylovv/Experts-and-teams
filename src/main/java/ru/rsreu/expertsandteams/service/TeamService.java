package ru.rsreu.expertsandteams.service;

import ru.rsreu.expertsandteams.model.api.response.TeamResponse;
import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.model.entity.User;

import java.util.List;

public interface TeamService {
    Team findById(Long id);

    List<TeamResponse> findUserTeams(User user);

    void leaveTeam(Long teamId, User user);

    void createTeam(String teamName, Long captainId);

    List<TeamResponse> findAvailableTeams(Long userId);
}

package ru.rsreu.expertsandteams.service;

import ru.rsreu.expertsandteams.model.api.response.TeamResponse;
import ru.rsreu.expertsandteams.model.entity.Team;

import java.util.List;

public interface TeamService {
    Team findById(Long id);

    List<TeamResponse> findUserTeams(Long userId);

    void leaveTeam(Long teamId, Long userId);

    void createTeam(String teamName, Long captainId);

    List<TeamResponse> findAvailableTeams(Long userId);
}

package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.api.response.TeamResponse;
import ru.rsreu.expertsandteams.model.entity.Team;

public class TeamMapper {
    private TeamMapper() {
    }

    public static TeamResponse mapToTeamResponse(Team team) {
        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getMembersCount(),
                team.getCaptain().getId()
        );
    }
}

package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.api.response.JoinTeamResponse;
import ru.rsreu.expertsandteams.model.entity.TeamJoinRequest;

public class TeamJoinRequestMapper {
    private TeamJoinRequestMapper() {
    }

    public static JoinTeamResponse mapToJoinTeamResponse(TeamJoinRequest teamJoinRequest) {
        return new JoinTeamResponse(
                teamJoinRequest.getId(),
                teamJoinRequest.getUser().getName(),
                teamJoinRequest.getMessage()
        );
    }
}

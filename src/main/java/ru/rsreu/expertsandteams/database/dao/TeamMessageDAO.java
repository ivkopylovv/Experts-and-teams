package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.TeamMessage;

import java.util.List;
import java.util.Optional;

public interface TeamMessageDAO {
    List<TeamMessage> findByTeamId(Long teamId);

    Optional<TeamMessage> save(TeamMessage message);

    List<TeamMessage> findActualMessagesByTeamIdAndUserId(Long teamId, Long userId);
}

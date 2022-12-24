package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.LastMessageRequest;

import java.util.Optional;

public interface LastMessageRequestDAO {
    void upsert(Long teamId, Long userId);

    Optional<LastMessageRequest> findByTeamIdAndUserId(Long teamId, Long userId);
}

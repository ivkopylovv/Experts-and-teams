package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.LastMessageRequest;

import java.util.Optional;

/**
 * The Data Access Object that providing access
 * to LastMessageRequest Entity.
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 *
 */
public interface LastMessageRequestDAO {
    /**
     * If Entity exists - Updates LastMessageRequest Entity in DB
     * Else - Saves LastMessageRequest Entity in DB
     *
     * @param teamId ID of required Team Entity
     * @param userId ID of required User Entity
     */
    void upsert(Long teamId, Long userId);

    /**
     * Finds LastMessageRequest by teamID and userID
     *
     * @param teamId ID of required Team Entity
     * @param userId ID of required User Entity
     * @return object of found LastMessageRequest in the Optional wrapper
     */
    Optional<LastMessageRequest> findByTeamIdAndUserId(Long teamId, Long userId);
}

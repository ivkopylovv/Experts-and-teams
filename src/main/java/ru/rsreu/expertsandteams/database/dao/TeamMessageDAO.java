package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.TeamMessage;

import java.util.List;
import java.util.Optional;

/**
 * The Data Access Object that providing access
 * to TeamMessage Entity.
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 *
 */
public interface TeamMessageDAO {
    /**
     * Finds all TeamMessage Entities by teamID
     *
     * @param teamId ID of required Team Entity
     * @return list of found TeamMessage objects
     */
    List<TeamMessage> findByTeamId(Long teamId);

    /**
     * Saves TeamMessage Entity to DB
     *
     * @param message required TeamMessage to save
     * @return object of saved TeamMessage in the Optional wrapper
     */
    Optional<TeamMessage> save(TeamMessage message);

    /**
     * Finds all TeamMessage Entities by teamID
     * after last message request by User with userID
     *
     * @param teamId ID of required Team Entity
     * @param userId ID of required User Entity
     * @return list of found TeamMessage objects
     */
    List<TeamMessage> findActualMessagesByTeamIdAndUserId(Long teamId, Long userId);
}

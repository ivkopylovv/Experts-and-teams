package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.TeamJoinRequest;

import java.util.List;
import java.util.Optional;

/**
 * The Data Access Object that providing access
 * to TeamJoinRequest Entity.
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 *
 */
public interface TeamJoinRequestDAO {
    /**
     * Finds for a TeamJoinRequest Entity by ID
     *
     * @param id ID of required TeamJoinRequest Entity
     * @return object of found TeamJoinRequest in the Optional wrapper
     */
    Optional<TeamJoinRequest> findById(Long id);

    /**
     * Saves TeamJoinRequest Entity to DB
     *
     * @param teamJoinRequest required TeamJoinRequest Entity to save
     */
    void save(TeamJoinRequest teamJoinRequest);

    /**
     * Finds TeamJoinRequest Entities by captainID of the desired Team
     *
     * @param captainId ID of required User Entity
     * @return list of found TeamJoinRequest objects
     */
    List<TeamJoinRequest> findByCaptainId(Long captainId);

    /**
     * Deletes TeamJoinRequest Entity from DB
     *
     * @param id ID of required TeamJoinRequest Entity
     */
    void deleteById(Long id);
}

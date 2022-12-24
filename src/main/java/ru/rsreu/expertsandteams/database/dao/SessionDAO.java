package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.Session;

import java.util.List;
import java.util.Optional;

/**
 * The Data Access Object that providing access
 * to Session Entity.
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 *
 */
public interface SessionDAO {
    /**
     * Finds for a Session Entity by ID
     *
     * @param userId ID of required User Entity
     * @return object of found Session in the Optional wrapper
     */
    Optional<Session> findByUserId(Long userId);

    /**
     * Saves Session Entity to DB
     *
     * @param session required Session Entity to save
     */
    void save(Session session);

    /**
     * Deletes Session Entity from DB by userId
     *
     * @param userId ID of required User Entity
     */
    void deleteByUserId(Long userId);

    /**
     * Finds for all current Session Entities in the system,
     * excluding the requesting user
     *
     * @param userId ID of requesting User
     * @return list of found Session objects
     */
    List<Session> findAll(Long userId);
}

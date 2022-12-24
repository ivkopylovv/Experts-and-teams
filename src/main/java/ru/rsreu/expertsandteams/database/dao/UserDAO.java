package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.ExpertDetail;
import ru.rsreu.expertsandteams.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The Data Access Object that providing access
 * to User Entity.
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 *
 */
public interface UserDAO {
    /**
     * Finds for a User Entity by ID
     *
     * @param id ID of required User Entity
     * @return object of found User in the Optional wrapper
     */
    Optional<User> findById(Long id);

    /**
     * Finds for a User Entity by Username
     *
     * @param username Username of required User Entity
     * @return object of found User in the Optional wrapper
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds for User Entities, excluding
     * the requesting user and User Entities
     * with Role - Administrator
     *
     * @param id ID of requesting User
     * @return list of found User objects
     */
    List<User> findAllWithoutAdmins(Long id);

    /**
     * Changes the block status to the opposite
     *
     * @param user required User Entity to change the status
     */
    void changeBlockStatus(User user);

    /**
     * Saves details of Expert Entity to DB
     *
     * @param expertDetail required ExpertDetail Entity to save
     */
    void saveExpertDetails(ExpertDetail expertDetail);

    /**
     * Deletes User Entity from DB
     *
     * @param user required User Entity to delete
     */
    void delete(User user);

    /**
     * Deletes User Entity from DB by ID
     *
     * @param id ID of required User Entity to delete
     */
    void deleteById(Long id);

    /**
     * Saves User Entity to DB
     *
     * @param user required User Entity to save
     * @return object of saved User in the Optional wrapper
     */
    Optional<User> save(User user);

    /**
     * Updates User Entity information in DB
     *
     * @param user required User Entity to update
     */
    void update(User user);

    /**
     * Increases the current count of Expert teams by 1
     *
     * @param expertId ID of required User Entity
     */
    void incrementExpertTeamsCount(Long expertId);

    /**
     * Reduces the current count of Expert teams by 1
     *
     * @param expertId ID of required User Entity
     */
    void decrementExpertTeamsCount(Long expertId);

    /**
     * Finds available Experts for team
     *
     * @param teamId ID of required Team Entity
     * @return list of found User objects
     */
    List<User> findAvailableExperts(Long teamId);

    /**
     * Finds current Experts of team
     *
     * @param teamId ID of required Team Entity
     * @return list of found User objects
     */
    List<User> findTeamExperts(Long teamId);
}

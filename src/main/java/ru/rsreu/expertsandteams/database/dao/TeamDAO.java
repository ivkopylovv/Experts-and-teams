package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.Team;

import java.util.List;
import java.util.Optional;

/**
 * The Data Access Object that providing access
 * to Team Entity.
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 *
 */
public interface TeamDAO {
    /**
     * Finds for a Team Entity by ID
     *
     * @param id ID of required Team Entity
     * @return object of found Team in the Optional wrapper
     */
    Optional<Team> findById(Long id);

    /**
     * Finds for Team Entities by userID,
     * which the User is a member of
     * @param userId ID of required User Entity
     * @return list of found Team objects
     */
    List<Team> findByUserId(Long userId);

    /**
     * Deletes Team Entity from DB
     *
     * @param id ID of required Team Entity
     */
    void deleteById(Long id);

    /**
     * Finds for Team Entities by expertID,
     * which the Expert advises
     *
     * @param expertId ID of required User Entity
     * @return list of found Team objects
     */
    List<Team> findByExpertId(Long expertId);

    /**
     * Saves Team Entity to DB
     *
     * @param team required Team Entity to save
     * @return object of saved Team in the Optional wrapper
     */
    Optional<Team> save(Team team);

    /**
     * Saves new Team Member to Team Entity
     *
     * @param teamId ID of required Team Entity
     * @param userId ID of required User Entity
     */
    void addTeamMember(Long teamId, Long userId);

    /**
     * Saves new Team Expert to Team Entity
     *
     * @param teamId ID of required Team Entity
     * @param expertId ID of required User Entity
     */
    void addExpert(Long teamId, Long expertId);

    /**
     * Deletes Team Expert from Team Entity
     *
     * @param teamId ID of required Team Entity
     * @param expertId ID of required User Entity
     */
    void deleteExpert(Long teamId, Long expertId);

    /**
     * Deletes Team Member from Team Entity
     *
     * @param teamId ID of required Team Entity
     * @param userId ID of required User Entity
     */
    void deleteTeamMember(Long teamId, Long userId);

    /**
     * Finds Team Entities available for User to join
     *
     * @param userId ID of required User Entity
     * @return list of found Team objects
     */
    List<Team> findAvailableTeams(Long userId);

    /**
     * Increases the current count of Team members by 1
     *
     * @param id ID of required Team Entity
     */
    void incrementTeamMembers(Long id);

    /**
     * Reduces the current count of Team members by 1
     *
     * @param id ID of required Team Entity
     */
    void decrementTeamMembers(Long id);

    /**
     * Updates the Expert block status to the opposite
     *
     * @param teamId ID of required Team Entity
     * @param expertId ID of required User Entity
     * @param previousStatus previous block status of Expert
     */
    void updateExpertBlockStatus(Long teamId, Long expertId, Boolean previousStatus);
}

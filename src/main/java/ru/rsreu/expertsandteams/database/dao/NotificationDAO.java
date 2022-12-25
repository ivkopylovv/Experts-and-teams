package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.Notification;

import java.util.List;

/**
 * The Data Access Object that providing access
 * to Notification Entity.
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 *
 */
public interface NotificationDAO {

    /**
     * Saves Notification Entity to DB
     *
     * @param notification required Notification Entity to save
     */
    void save(Notification notification);

    /**
     * Deletes Notification Entity from DB by ID
     *
     * @param id ID of required Notification Entity
     */
    void deleteById(Long id);

    /**
     * Finds for all Notification Entities
     * of User with userID
     * @param userId ID of requesting User
     * @return list of found Notification objects
     */
    List<Notification> findByUserId(Long userId);
}

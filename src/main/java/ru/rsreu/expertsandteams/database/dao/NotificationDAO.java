package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.Notification;

import java.util.List;

public interface NotificationDAO {
    void save(Notification notification);

    void deleteById(Long id);

    List<Notification> findByUserId(Long userId);
}

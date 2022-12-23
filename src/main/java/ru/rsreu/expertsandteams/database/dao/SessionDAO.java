package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.Session;

import java.util.List;
import java.util.Optional;

public interface SessionDAO {
    Optional<Session> findByUserId(Long userId);

    void save(Session session);

    void deleteByUserId(Long userId);

    List<Session> findAll(Long userId);
}

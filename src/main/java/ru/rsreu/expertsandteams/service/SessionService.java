package ru.rsreu.expertsandteams.service;

import ru.rsreu.expertsandteams.model.entity.Session;

import java.util.Optional;

public interface SessionService {
    Optional<Session> getSession(int userId);
}

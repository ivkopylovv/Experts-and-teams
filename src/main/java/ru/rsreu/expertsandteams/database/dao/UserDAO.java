package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.data.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    List<User> findAllWithSession();

    void addRoleToUser(User user, Role role);

    List<User> findAllByBlockStatus(boolean isBlocked);

    List<User> findAllAuthorized();

    List<User> findAllByTeamId(long teamId);

    Optional<User> save(User user);

    Optional<User> update(User user);
}

package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.data.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    ArrayList<User> findAll();

    void addRoleToUser(User user, Role role);

    ArrayList<User> findAllByBlockStatus(boolean isBlocked);

    ArrayList<User> findAllAuthorized();

    ArrayList<User> findAllByTeamId(long teamId);

    User save(User user);
}

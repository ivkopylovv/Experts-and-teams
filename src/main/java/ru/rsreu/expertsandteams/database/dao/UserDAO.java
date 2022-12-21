package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    List<User> findAllByTeamId(long teamId);

    List<User> findAllWithoutAdmins();

    void changeBlockStatus(User user);

    void delete(User user);

    void deleteById(Long id);

    Optional<User> save(User user);

    void update(User user);
}

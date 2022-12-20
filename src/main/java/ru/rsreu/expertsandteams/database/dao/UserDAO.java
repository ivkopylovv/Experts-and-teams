package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

//    List<User> findAllWithSession();

//    void addRoleToUser(User user, Role role);

    List<User> findAllByTeamId(long teamId);

//    List<User> findAllWithoutAdmins();

    void changeBlockStatus(List<Long> userIds);

    void delete(List<Long> userIds);

    Optional<User> save(User user);

    Optional<User> update(User user);
}

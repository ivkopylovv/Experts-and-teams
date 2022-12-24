package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findAll(Long id);

    List<User> findAllWithoutAdmins(Long id);

    void changeBlockStatus(User user);

    void saveExpertDetails(Long expertId);

    void delete(User user);

    void deleteById(Long id);

    Optional<User> save(User user);

    void update(User user);

    void incrementExpertTeamsCount(Long expertId);

    void decrementExpertTeamsCount(Long expertId);

    List<User> findAvailableExperts(Long teamId);

    List<User> findTeamExperts(Long teamId);
}

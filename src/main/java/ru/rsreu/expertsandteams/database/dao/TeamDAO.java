package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamDAO {
    Optional<Team> findById(Long id);

    List<Team> findByUserId(Long userId);

    Optional<Team> save(Team team);

    void addTeamMember(Long teamId, Long userId);

    void addExpert(Long teamId, Long expertId);

    void deleteById(Long id);

    void deleteTeamMember(Long teamId, Long userId);

    List<Team> findAvailableTeams(Long userId);
}

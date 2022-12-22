package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.TeamJoinRequest;

import java.util.List;
import java.util.Optional;

public interface TeamJoinRequestDAO {
    Optional<TeamJoinRequest> findById(Long id);

    void save(TeamJoinRequest teamJoinRequest);

    List<TeamJoinRequest> findByCaptainId(Long captainId);

    void deleteById(Long id);
}

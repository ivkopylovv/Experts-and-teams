package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.api.response.ChatResponse;
import ru.rsreu.expertsandteams.model.entity.TeamMessage;

import java.util.List;

public interface TeamMessageDAO {
    List<TeamMessage> findByTeamId(Long teamId);

    void save(TeamMessage message);
}

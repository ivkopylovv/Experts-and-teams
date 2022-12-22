package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.api.response.ChatResponse;
import ru.rsreu.expertsandteams.model.entity.TeamMessage;

public interface TeamMessageDAO {
    ChatResponse findByTeamId(Long teamId);

    void save(TeamMessage message);
}

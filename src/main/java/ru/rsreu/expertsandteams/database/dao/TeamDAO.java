package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.data.Team;

import java.util.List;

public interface TeamDAO {
    List<Team> findAll();
}

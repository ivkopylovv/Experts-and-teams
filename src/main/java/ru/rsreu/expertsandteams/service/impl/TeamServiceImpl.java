package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.TeamDAO;
import ru.rsreu.expertsandteams.service.TeamService;

import java.util.List;

public class TeamServiceImpl implements TeamService {
    private static volatile TeamServiceImpl instance;

    private TeamDAO teamDAO;

    public TeamServiceImpl(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public List<Team> getAllTeams() {
//        return teamDAO.findAll();
        return List.of();
    }

    public static TeamServiceImpl getInstance() {
        synchronized (TeamServiceImpl.class) {
            if (instance == null) {
                instance = new TeamServiceImpl(
                        DAOFactory.getTeamDAO()
                );
            }
        }

        return instance;
    }
}

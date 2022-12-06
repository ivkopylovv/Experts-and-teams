package ru.rsreu.expertsandteams.database.dao.impl;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.data.Team;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.database.dao.TeamDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAOImpl implements TeamDAO {
    private static volatile TeamDAOImpl instance;
    private final Resourcer resourcer;

    private TeamDAOImpl() {
        resourcer = ProjectResourcer.getInstance();
    }

    public List<Team> findAll() {
        String query = resourcer.getString("team.query.find.all");
        List<Team> teams = new ArrayList<>();

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Team team = new Team(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("members_count"),
                        resultSet.getLong("captain_id")
                );

                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }

    public static TeamDAOImpl getInstance() {
        synchronized (TeamDAOImpl.class) {
            if (instance == null) {
                instance = new TeamDAOImpl();
            }
        }

        return instance;
    }
}

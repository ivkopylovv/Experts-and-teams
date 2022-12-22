package ru.rsreu.expertsandteams.database.dao.impl;

import ru.rsreu.expertsandteams.database.dao.AbstractDAO;
import ru.rsreu.expertsandteams.database.dao.TeamMessageDAO;
import ru.rsreu.expertsandteams.model.api.response.ChatResponse;
import ru.rsreu.expertsandteams.model.entity.TeamMessage;

import java.sql.*;

import static java.sql.Types.*;

public class TeamMessageDAOImpl extends AbstractDAO implements TeamMessageDAO {
    private static volatile TeamMessageDAOImpl instance;

    @Override
    public ChatResponse findByTeamId(Long teamId) {
        return null;
    }

    @Override
    public void save(TeamMessage message) {
        String query = resourcer.getString("team.message.query.find.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, message.getTeam().getId());
            st.setLong(2, message.getUser().getId());
            st.setString(3, message.getMessage());
            st.setDate(4, new Date(System.currentTimeMillis()));
            if (message.getExpert() != null) {
                st.setLong(5, message.getExpert().getId());
            } else {
                st.setNull(5, NULL);
            }

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TeamMessageDAOImpl getInstance() {
        synchronized (TeamMessageDAOImpl.class) {
            if (instance == null) {
                instance = new TeamMessageDAOImpl();
            }
        }

        return instance;
    }
}

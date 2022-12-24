package ru.rsreu.expertsandteams.database.dao.impl;

import ru.rsreu.expertsandteams.database.dao.AbstractDAO;
import ru.rsreu.expertsandteams.database.dao.LastMessageRequestDAO;
import ru.rsreu.expertsandteams.model.entity.LastMessageRequest;
import ru.rsreu.expertsandteams.support.mapper.DAOMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LastMessageRequestDAOImpl extends AbstractDAO implements LastMessageRequestDAO {
    private static volatile LastMessageRequestDAOImpl instance;

    @Override
    public void upsert(Long teamId, Long userId) {
        String query = resourcer.getString("last.message.request.query.upsert");
        Date currentDate = new Date(System.currentTimeMillis());

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, teamId);
            st.setLong(2, userId);
            st.setDate(3, currentDate);
            st.setLong(4, teamId);
            st.setLong(5, userId);
            st.setLong(6, teamId);
            st.setLong(7, userId);
            st.setDate(8, currentDate);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<LastMessageRequest> findByTeamIdAndUserId(Long teamId, Long userId) {
        String query = resourcer.getString("last.message.find.by.team.id.and.user.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, teamId);
            st.setLong(2, userId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return Optional.ofNullable(DAOMapper.mapToLastMessageRequest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public static LastMessageRequestDAOImpl getInstance() {
        synchronized (LastMessageRequestDAOImpl.class) {
            if (instance == null) {
                instance = new LastMessageRequestDAOImpl();
            }
        }

        return instance;
    }
}

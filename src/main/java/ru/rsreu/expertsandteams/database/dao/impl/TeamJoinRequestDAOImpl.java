package ru.rsreu.expertsandteams.database.dao.impl;

import ru.rsreu.expertsandteams.database.dao.AbstractDAO;
import ru.rsreu.expertsandteams.database.dao.TeamJoinRequestDAO;
import ru.rsreu.expertsandteams.model.entity.TeamJoinRequest;
import ru.rsreu.expertsandteams.support.mapper.DAOMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamJoinRequestDAOImpl extends AbstractDAO implements TeamJoinRequestDAO {
    private static volatile TeamJoinRequestDAOImpl instance;

    @Override
    public Optional<TeamJoinRequest> findById(Long id) {
        String query = resourcer.getString("team.join.request.query.find.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return Optional.ofNullable(DAOMapper.mapToFullTeamJoinRequest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void save(TeamJoinRequest teamJoinRequest) {
        String query = resourcer.getString("team.join.request.query.save");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, teamJoinRequest.getUser().getId());
            st.setLong(2, teamJoinRequest.getTeam().getId());
            st.setString(3, teamJoinRequest.getMessage());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TeamJoinRequest> findByCaptainId(Long captainId) {
        String query = resourcer.getString("team.join.request.query.find.by.captain.id");
        List<TeamJoinRequest> teamJoinRequests = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, captainId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                teamJoinRequests.add(DAOMapper.mapToTeamJoinRequest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teamJoinRequests;
    }

    @Override
    public void deleteById(Long id) {
        String query = resourcer.getString("team.join.request.query.delete.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TeamJoinRequestDAOImpl getInstance() {
        synchronized (TeamJoinRequestDAOImpl.class) {
            if (instance == null) {
                instance = new TeamJoinRequestDAOImpl();
            }
        }

        return instance;
    }
}

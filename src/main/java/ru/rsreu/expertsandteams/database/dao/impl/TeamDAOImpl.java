package ru.rsreu.expertsandteams.database.dao.impl;


import ru.rsreu.expertsandteams.database.dao.AbstractDAO;
import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.database.dao.TeamDAO;
import ru.rsreu.expertsandteams.support.mapper.DAOMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamDAOImpl extends AbstractDAO implements TeamDAO {
    private static volatile TeamDAOImpl instance;

    @Override
    public Optional<Team> findById(Long id) {
        String query = resourcer.getString("team.query.find.by,id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return Optional.ofNullable(DAOMapper.mapToTeam(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Team> findByUserId(Long userId) {
        String query = resourcer.getString("team.query.find.by.user.id");
        List<Team> teams = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, userId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                teams.add(DAOMapper.mapToTeam(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }

    @Override
    public Optional<Team> save(Team team) {
        String query = resourcer.getString("team.query.save");
        String[] returnId = {"id"};

        try (PreparedStatement st = connection.prepareStatement(query, returnId)) {
            st.setString(1, team.getName());
            st.setLong(2, team.getCaptain().getId());

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating team failed");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);

                    team.setId(id);

                    return Optional.ofNullable(team);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void addTeamMember(Long teamId, Long userId) {
        String query = resourcer.getString("team.query.add.team.member");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, teamId);
            st.setLong(2, userId);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addExpert(Long teamId, Long expertId) {
        String query = resourcer.getString("team.query.add.expert");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, teamId);
            st.setLong(2, expertId);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        String query = resourcer.getString("team.query.delete.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeamMember(Long teamId, Long userId) {
        String query = resourcer.getString("team.query.delete.team.member");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, teamId);
            st.setLong(2, userId);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Team> findAvailableTeams(Long userId) {
        String query = resourcer.getString("team.query.find.available.teams");
        List<Team> teams = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, userId);
            st.setLong(2, userId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                teams.add(DAOMapper.mapToTeam(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }

    @Override
    public void incrementTeamMembers(Long id) {
        String query = resourcer.getString("team.query.increment.members.count");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void decrementTeamMembers(Long id) {
        String query = resourcer.getString("team.query.decrement.members.count");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

package ru.rsreu.expertsandteams.database.dao.impl;

import ru.rsreu.expertsandteams.database.dao.AbstractDAO;
import ru.rsreu.expertsandteams.database.dao.NotificationDAO;
import ru.rsreu.expertsandteams.model.entity.Notification;
import ru.rsreu.expertsandteams.support.mapper.DAOMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOImpl extends AbstractDAO implements NotificationDAO {
    private static volatile NotificationDAOImpl instance;

    @Override
    public void save(Notification notification) {
        String query = resourcer.getString("notification.query.save");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, notification.getUser().getId());
            st.setString(2, notification.getMessage());
            st.setDate(3, new Date(System.currentTimeMillis()));
            st.setBoolean(4, notification.getAccepted());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        String query = resourcer.getString("notification.query.delete.by.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notification> findByUserId(Long userId) {
        String query = resourcer.getString("notification.query.find.by.user.id");
        List<Notification> notifications = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, userId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                notifications.add(DAOMapper.mapToNotification(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }

    public static NotificationDAOImpl getInstance() {
        synchronized (NotificationDAOImpl.class) {
            if (instance == null) {
                instance = new NotificationDAOImpl();
            }
        }

        return instance;
    }
}

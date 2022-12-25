package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.NotificationDAO;
import ru.rsreu.expertsandteams.model.api.request.RemoveNotificationRequest;
import ru.rsreu.expertsandteams.model.api.response.NotificationResponse;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.service.NotificationService;
import ru.rsreu.expertsandteams.support.mapper.NotificationMapper;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationServiceImpl implements NotificationService {
    private static volatile NotificationServiceImpl instance;

    private final NotificationDAO notificationDAO;

    public NotificationServiceImpl(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    @Override
    public void deleteNotification(RemoveNotificationRequest request) {
        notificationDAO.deleteById(request.getNotificationId());
    }

    @Override
    public List<NotificationResponse> findAllUserNotifications(User user) {
        return notificationDAO.findByUserId(user.getId())
                .stream()
                .map(NotificationMapper::mapToNotificationResponse)
                .collect(Collectors.toList());
    }

    public static NotificationServiceImpl getInstance() {
        synchronized (NotificationServiceImpl.class) {
            if (instance == null) {
                instance = new NotificationServiceImpl(
                        DAOFactory.getNotificationDAO()
                );
            }
        }

        return instance;
    }
}

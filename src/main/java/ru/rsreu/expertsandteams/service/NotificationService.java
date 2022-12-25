package ru.rsreu.expertsandteams.service;

import ru.rsreu.expertsandteams.model.api.request.RemoveNotificationRequest;
import ru.rsreu.expertsandteams.model.api.response.NotificationResponse;
import ru.rsreu.expertsandteams.model.entity.User;

import java.util.List;

public interface NotificationService {
    void deleteNotification(RemoveNotificationRequest request);

    List<NotificationResponse> findAllUserNotifications(User user);
}

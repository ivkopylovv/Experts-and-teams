package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.api.response.NotificationResponse;
import ru.rsreu.expertsandteams.model.entity.Notification;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.support.helper.DateHelper;

import static ru.rsreu.expertsandteams.constant.ActionMessage.*;

public class NotificationMapper {
    private NotificationMapper() {
    }

    public static Notification mapToNotification(User user, User captain, Boolean isAccepted) {
        return new Notification(
                user,
                isAccepted ? String.format(ACCEPT_JOIN_TEAM, captain.getName())
                        : String.format(DENIED_JOIN_TEAM, captain.getName())
        );
    }

    public static NotificationResponse mapToNotificationResponse(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getMessage(),
                DateHelper.getDateAsString(notification.getNotificationDate())
        );
    }
}

package ru.rsreu.expertsandteams.model.api.request;

public class RemoveNotificationRequest {
    private Long notificationId;

    public RemoveNotificationRequest(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }
}

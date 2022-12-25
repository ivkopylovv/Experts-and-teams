package ru.rsreu.expertsandteams.model.api.response;

public class NotificationResponse {
    private Long id;
    private String message;
    private String date;

    public NotificationResponse(Long id, String message, String date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

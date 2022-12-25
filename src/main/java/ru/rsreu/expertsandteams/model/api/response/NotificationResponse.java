package ru.rsreu.expertsandteams.model.api.response;

public class NotificationResponse {
    private Long id;
    private String message;
    private String date;
    private Boolean isAccepted;

    public NotificationResponse(Long id, String message, String date, Boolean isAccepted) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.isAccepted = isAccepted;
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

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }
}

package ru.rsreu.expertsandteams.model.api.response;

public class MessageResponse {
    private Long userId;
    private String userName;
    private String expertName;
    private String message;
    private String date;

    public MessageResponse(Long userId, String userName, String expertName, String message, String date) {
        this.userId = userId;
        this.userName = userName;
        this.expertName = expertName;
        this.message = message;
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
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

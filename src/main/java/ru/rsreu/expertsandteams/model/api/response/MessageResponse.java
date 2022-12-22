package ru.rsreu.expertsandteams.model.api.response;

import java.util.Date;

public class MessageResponse {
    private String userName;
    private String expertName;
    private String message;
    private Date date;

    public MessageResponse(String userName, String expertName, String message, Date date) {
        this.userName = userName;
        this.expertName = expertName;
        this.message = message;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

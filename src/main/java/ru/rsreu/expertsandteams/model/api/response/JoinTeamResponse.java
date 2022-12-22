package ru.rsreu.expertsandteams.model.api.response;

public class JoinTeamResponse {
    private Long id;
    private String userName;
    private String message;

    public JoinTeamResponse(Long id, String userName, String message) {
        this.id = id;
        this.userName = userName;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

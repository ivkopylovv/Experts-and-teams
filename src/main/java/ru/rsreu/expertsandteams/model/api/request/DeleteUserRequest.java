package ru.rsreu.expertsandteams.model.api.request;

public class DeleteUserRequest {
    private Long userId;

    public DeleteUserRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

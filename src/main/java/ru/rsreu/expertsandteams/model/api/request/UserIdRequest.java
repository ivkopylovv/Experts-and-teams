package ru.rsreu.expertsandteams.model.api.request;

public class UserIdRequest {
    private Long id;

    public UserIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

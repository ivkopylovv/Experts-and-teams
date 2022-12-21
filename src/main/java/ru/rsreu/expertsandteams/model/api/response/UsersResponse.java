package ru.rsreu.expertsandteams.model.api.response;

import java.util.ArrayList;
import java.util.List;

public class UsersResponse {
    private List<UserResponse> userResponses = new ArrayList<>();

    public UsersResponse(List<UserResponse> userResponses) {
        this.userResponses = userResponses;
    }

    public List<UserResponse> getUserResponses() {
        return userResponses;
    }

    public void setUserResponses(List<UserResponse> userResponses) {
        this.userResponses = userResponses;
    }
}

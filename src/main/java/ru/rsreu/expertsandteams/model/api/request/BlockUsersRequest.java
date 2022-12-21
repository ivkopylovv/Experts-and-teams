package ru.rsreu.expertsandteams.model.api.request;

import java.util.List;

public class BlockUsersRequest {
    private List<Long> userIds;

    public BlockUsersRequest(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}

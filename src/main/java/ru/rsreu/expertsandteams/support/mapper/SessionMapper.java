package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.api.response.UserResponse;
import ru.rsreu.expertsandteams.model.entity.Session;
import ru.rsreu.expertsandteams.model.entity.User;

public class SessionMapper {
    private SessionMapper() {
    }

    public static UserResponse mapToUserResponse(Session session) {
        User user = session.getUser();

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                null,
                user.isOnline(session.getExpiredAt()),
                user.isBlocked()
        );
    }
}

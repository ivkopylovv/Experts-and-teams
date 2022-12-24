package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.api.request.AddUserRequest;
import ru.rsreu.expertsandteams.model.api.request.EditUserRequest;
import ru.rsreu.expertsandteams.model.api.request.SignUpRequest;
import ru.rsreu.expertsandteams.model.api.response.AvailableExpertResponse;
import ru.rsreu.expertsandteams.model.api.response.TeamExpertsResponse;
import ru.rsreu.expertsandteams.model.api.response.UserResponse;
import ru.rsreu.expertsandteams.model.entity.ExpertSkill;
import ru.rsreu.expertsandteams.model.entity.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {
    }

    public static User mapToUser(AddUserRequest userRequest) {
        return new User(
                userRequest.getName(),
                userRequest.getUsername(),
                userRequest.getPassword(),
                userRequest.getRole()
        );
    }

    public static User mapToUser(SignUpRequest userRequest) {
        return new User(
                userRequest.getName(),
                userRequest.getUsername(),
                userRequest.getPassword(),
                userRequest.getRole()
        );
    }

    public static User mapToUser(EditUserRequest userRequest) {
        return new User(
                userRequest.getId(),
                userRequest.getName(),
                userRequest.getUsername(),
                userRequest.getPassword()
        );
    }

    public static UserResponse mapToUserResponse(User user, Date expiredAt) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                null,
                user.isOnline(expiredAt),
                user.getBlocked()
        );
    }

    public static UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                null,
                null,
                user.getBlocked()
        );
    }

    public static AvailableExpertResponse mapToAvailableExpertResponse(User user, List<ExpertSkill> expertSkills) {
        return new AvailableExpertResponse(
                user.getId(),
                user.getName(),
                user.getBlocked(),
                expertSkills
                        .stream()
                        .map(ExpertSkill::getSkill)
                        .collect(Collectors.toList())
        );
    }

    public static TeamExpertsResponse mapToTeamExpertsResponse(User user,  List<ExpertSkill> expertSkills) {
        return new TeamExpertsResponse(
                user.getId(),
                user.getName(),
                user.getBlocked(),
                expertSkills
                        .stream()
                        .map(ExpertSkill::getSkill)
                        .collect(Collectors.toList())
        );
    }

}

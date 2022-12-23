package ru.rsreu.expertsandteams.service;


import ru.rsreu.expertsandteams.model.api.request.*;
import ru.rsreu.expertsandteams.model.api.response.RoleResponse;
import ru.rsreu.expertsandteams.model.api.response.UserResponse;
import ru.rsreu.expertsandteams.model.entity.User;

import java.util.List;

public interface UserService {
    void addUser(AddUserRequest addUserRequest);

    void signUp(SignUpRequest signUpRequest);

    RoleResponse signIn(SignInRequest signInRequest);

    void deleteUser(DeleteUserRequest deleteUserRequest);

    void editUser(EditUserRequest editUserRequest);

    User findById(Long id);

    User findByUsername(String username);

    void changeBlockStatus(BlockUsersRequest blockUsersRequest);

    void logout(User user);

    List<UserResponse> getModerDashboardUsers(Long userId);

    List<UserResponse> getAdminDashboardUsers(Long userId);
}

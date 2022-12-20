package ru.rsreu.expertsandteams.service;


import ru.rsreu.expertsandteams.model.api.request.SignInRequest;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Role;
import java.util.List;

public interface UserService {
//    void addUser(
//            String name,
//            String username,
//            String password,
//            String[] skills,
//            Role role
//    );
//
//    void updateUser(
//            Long id,
//            String name,
//            String username,
//            String password,
//            Boolean isBlocked
//    );
//
    void deleteUsers(List<Long> userIds);

    void changeBlockStatus(List<Long> userIds);

    User createSession(SignInRequest signInRequest);

    void deleteSession(User user);

    List<User> getAllUsersWithSession();
}

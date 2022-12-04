package ru.rsreu.expertsandteams.service;


import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.enums.RoleType;
import java.util.List;

public interface UserService {
    void addUser(
            String name,
            String username,
            String password,
            String[] skills,
            RoleType roleType
    );

    void updateUser(
            Long id,
            String name,
            String username,
            String password,
            Boolean isBlocked
    );

    List<User> getAllUsersWithSession();
}

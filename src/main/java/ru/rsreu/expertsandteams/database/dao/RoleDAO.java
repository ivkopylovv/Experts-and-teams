package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.data.Role;
import java.util.List;
import java.util.Optional;

public interface RoleDAO {
    List<Role> findByUserId(Long userId);
    Optional<Role> findByName(String roleName);
}

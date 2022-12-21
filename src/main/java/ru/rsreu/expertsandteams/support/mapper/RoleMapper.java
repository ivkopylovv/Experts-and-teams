package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.api.response.RoleResponse;
import ru.rsreu.expertsandteams.model.enums.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {
    public static RoleResponse mapToRoleResponse(String role) {
        return new RoleResponse(
                Role.valueOf(role.toUpperCase())
        );
    }
}

package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.enums.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {
    public static Role roleToRoleType(Role role) {
        return Role.valueOf(role.getName().toUpperCase());
    }

    public static List<Role> rolesToRoleTypes(List<Role> roles) {
        return roles
                .stream()
                .map(RoleMapper::roleToRoleType)
                .collect(Collectors.toList());
    }
}

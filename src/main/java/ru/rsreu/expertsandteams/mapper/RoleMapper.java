package ru.rsreu.expertsandteams.mapper;

import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.enums.RoleType;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {
    public static RoleType roleToRoleType(Role role) {
        return RoleType.valueOf(role.getName().toUpperCase());
    }

    public static List<RoleType> rolesToRoleTypes(List<Role> roles) {
        return roles
                .stream()
                .map(RoleMapper::roleToRoleType)
                .collect(Collectors.toList());
    }
}

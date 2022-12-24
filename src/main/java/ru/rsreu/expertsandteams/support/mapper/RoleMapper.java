package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.configuration.AuthConfig;
import ru.rsreu.expertsandteams.model.api.response.RedirectResponse;
import ru.rsreu.expertsandteams.model.api.response.RoleResponse;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Role;

public class RoleMapper {
    private RoleMapper() {
    }

    public static RoleResponse mapToRoleResponse(User user) {
        return new RoleResponse(
                Role.valueOf(user.getRole().toUpperCase()),
                user.getId()
        );
    }

    public static RedirectResponse mapToRedirectResponse(RoleResponse roleResponse) {
        return new RedirectResponse(
                AuthConfig.getStartPage(
                        roleResponse.getRole()
                ).getAbsolute()
        );
    }
}

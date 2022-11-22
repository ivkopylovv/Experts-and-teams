package ru.rsreu.expertsandteams.wrapper;

import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.enums.RoleType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;
import java.util.List;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {
    private final User user;
    private final List<RoleType> roleTypes;
    private final HttpServletRequest request;

    public UserRoleRequestWrapper(HttpServletRequest request, User user, List<RoleType> roleTypes) {
        super(request);

        this.user = user;
        this.roleTypes = roleTypes;
        this.request = request;
    }

    @Override
    public boolean isUserInRole(String roleAsString) {
        if (roleAsString == null) {
            return this.request.isUserInRole(null);
        }

        for (RoleType roleType : roleTypes) {
            if (roleType.getRole().equalsIgnoreCase(roleAsString)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return this.request.getUserPrincipal();
        }

        return user::getUsername;
    }
}

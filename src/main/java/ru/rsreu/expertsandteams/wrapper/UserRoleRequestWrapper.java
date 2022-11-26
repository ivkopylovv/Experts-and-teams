package ru.rsreu.expertsandteams.wrapper;

import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.enums.RoleType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {
    private final User user;
    private final HttpServletRequest request;

    public UserRoleRequestWrapper(HttpServletRequest request, User user) {
        super(request);

        this.request = request;
        this.user = user;
    }

    @Override
    public boolean isUserInRole(String roleAsString) {
        if (roleAsString == null) {
            return this.request.isUserInRole(null);
        }

        for (RoleType roleType : user.getRoles()) {
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

        return user;
    }
}

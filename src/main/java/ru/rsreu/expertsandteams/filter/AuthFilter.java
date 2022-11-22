package ru.rsreu.expertsandteams.filter;

import ru.rsreu.expertsandteams.container.RedirectContainer;
import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.RoleDAO;
import ru.rsreu.expertsandteams.enums.RoleType;
import ru.rsreu.expertsandteams.handler.RedirectHandler;
import ru.rsreu.expertsandteams.mapper.RoleMapper;
import ru.rsreu.expertsandteams.wrapper.UserRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthFilter implements Filter {
    private static final DAOFactory daoFactory = DAOFactory.getInstance();

    private RoleDAO roleDAO;

    @Override
    public void init(FilterConfig filterConfig) {
        roleDAO = daoFactory.getRoleDAO();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain next)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        RedirectHandler redirectHandler = RedirectHandler.getInstance();
        RedirectContainer redirectContainer = redirectHandler.handleRedirection(request);

        User user = redirectContainer.getUser();
        // TODO: think
        List<RoleType> roles = user != null
            ? RoleMapper.rolesToRoleTypes(roleDAO.findByUserId(user.getId()))
            : new ArrayList<>();
        HttpServletRequest wrappedRequest = user != null
            ? new UserRoleRequestWrapper(request, user, roles)
            : request;

        if (redirectContainer.getError() != null) {
            HttpSession session = request.getSession(true);

            // TODO: move to constants
            session.setAttribute("PUSH_ERROR", redirectContainer.getError());
            next.doFilter(wrappedRequest, response);

            return;
        }

        if (redirectContainer.getUser() != null) {
            next.doFilter(wrappedRequest, response);

            return;
        }

        next.doFilter(wrappedRequest, response);
    }
}

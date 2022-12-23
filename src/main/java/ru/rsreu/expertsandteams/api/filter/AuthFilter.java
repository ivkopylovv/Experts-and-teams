package ru.rsreu.expertsandteams.api.filter;

import ru.rsreu.expertsandteams.model.entity.Session;
import ru.rsreu.expertsandteams.api.wrapper.UserRoleRequestWrapper;
import ru.rsreu.expertsandteams.model.enums.Route;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.SessionService;
import ru.rsreu.expertsandteams.support.helper.SessionHelper;
import ru.rsreu.expertsandteams.support.helper.UserHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class AuthFilter implements Filter {
    private SessionService sessionService;

    @Override
    public void init(FilterConfig filterConfig) {
        sessionService = ServiceFactory.getSessionService();
    }

    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain next
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getPathInfo();

        if (path.equals(Route.NOT_FOUND.getRelative())) {
            next.doFilter(request, response);
            return;
        }

        Optional<Long> userId = UserHelper.getUserIdFromCookies(request.getCookies());
        Optional<Session> session = userId.isPresent()
                ? sessionService.getSession(userId.get())
                : Optional.empty();

        if (!session.isPresent() || !SessionHelper.checkValid(session.get())) {
            if (path.equals(Route.SIGNIN.getRelative())) {
                next.doFilter(request, response);
                return;
            }

            response.sendRedirect(Route.SIGNIN.getAbsolute());
            return;
        }

        HttpServletRequest wrapRequest = new UserRoleRequestWrapper(request, session.get().getUser());

        next.doFilter(wrapRequest, response);
    }
}

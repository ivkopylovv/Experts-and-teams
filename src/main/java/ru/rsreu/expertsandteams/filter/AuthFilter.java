package ru.rsreu.expertsandteams.filter;

import ru.rsreu.expertsandteams.container.RedirectContainer;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.handler.RedirectHandler;
import ru.rsreu.expertsandteams.wrapper.UserRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.PageOptions.PUSH_ERROR;
import static ru.rsreu.expertsandteams.constant.Routes.*;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain next
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        RedirectHandler redirectHandler = RedirectHandler.getInstance();
        RedirectContainer redirectContainer = redirectHandler.handleRedirection(request);

        User user = redirectContainer.getUser();
        HttpServletRequest wrappedRequest = user != null
                ? new UserRoleRequestWrapper(request, user)
                : request;
        String page = request.getPathInfo().substring(1);

        /**
         * If we try to route to auth pages, or we have no permissions
         */
        if (redirectContainer.getError() != null) {
            HttpSession session = request.getSession(true);

            session.setAttribute(PUSH_ERROR, redirectContainer.getError());
            response.sendRedirect(redirectContainer.getUrl());

            return;
        }

        /**
         * If we try to route to secure page, but we have no session or
         * try to route to auth pages without session
         */
        if (redirectContainer.getUser() == null && redirectContainer.getError() == null) {
            if (page.equals(SIGNIN) || page.equals(SIGNUP)) {
                next.doFilter(wrappedRequest, response);

                return;
            }

            response.sendRedirect(redirectContainer.getUrl());
            return;
        }

        /**
         * If we have permissions to route to the page
         */
        next.doFilter(wrappedRequest, response);
    }
}

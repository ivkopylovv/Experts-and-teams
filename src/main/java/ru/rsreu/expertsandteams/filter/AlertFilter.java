package ru.rsreu.expertsandteams.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.PageOptions.PUSH_ERROR;

public class AlertFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain next
    ) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String error = (String) session.getAttribute(PUSH_ERROR);

        if (error != null) {
            request.setAttribute(PUSH_ERROR, error);
            session.invalidate();
        }

        next.doFilter(request, response);
    }
}

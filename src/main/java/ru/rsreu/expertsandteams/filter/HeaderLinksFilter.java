package ru.rsreu.expertsandteams.filter;


import ru.rsreu.expertsandteams.config.AuthConfig;
import ru.rsreu.expertsandteams.constant.RouteNames;
import ru.rsreu.expertsandteams.data.Link;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.helper.UrlHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.rsreu.expertsandteams.constant.PageOptions.LINKS;

public class HeaderLinksFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain next
    ) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getUserPrincipal();

        if (user == null) {
            next.doFilter(request, response);

            return;
        }

        Set<String> pages = user.getRoles().stream()
                .map(role -> AuthConfig.getRoleUrlPatterns(role))
                .flatMap(targets -> targets.stream())
                .collect(Collectors.toSet());
        List<Link> links = pages.stream()
                .map(target -> new Link(RouteNames.getRouteName(target), UrlHelper.getUrlWithPrefix(target)))
                .collect(Collectors.toList());

        request.setAttribute(LINKS, links);

        next.doFilter(request, response);
    }
}

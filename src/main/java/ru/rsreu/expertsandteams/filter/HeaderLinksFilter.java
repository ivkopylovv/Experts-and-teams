package ru.rsreu.expertsandteams.filter;


import ru.rsreu.expertsandteams.data.Link;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.helper.UserLinkHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.rsreu.expertsandteams.constant.RequestAttribute.LINKS;

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

        List<String> pages = UserLinkHelper.getUserPages(user);
        List<Link> links = UserLinkHelper.getUserLinks(pages);

        request.setAttribute(LINKS, links);

        next.doFilter(request, response);
    }
}

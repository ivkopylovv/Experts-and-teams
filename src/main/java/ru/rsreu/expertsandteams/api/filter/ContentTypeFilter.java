package ru.rsreu.expertsandteams.api.filter;

import ru.rsreu.expertsandteams.constant.ContentType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ContentTypeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest)request).getRequestURI();

        response.setContentType(ContentType.DEFAULT);

        for (String fileType: ContentType.getFileTypes()) {
            if (uri.contains(fileType)) {
                String mimeType = ContentType.getContentType(fileType);

                response.setContentType(mimeType);
                break;
            }
        }

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        next.doFilter(request, response);
    }
}

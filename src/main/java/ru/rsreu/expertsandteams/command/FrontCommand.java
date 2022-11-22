package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.helper.UrlHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
    }

    public void process() throws ServletException, IOException {

    }

    public void send() throws ServletException, IOException {
    }

    protected void forward(String page) throws ServletException, IOException {
        String pageUrl = UrlHelper.getPageUrl(page);
        RequestDispatcher dispatcher = context.getRequestDispatcher(pageUrl);

        UrlHelper.setUrlHeader(response, page);
        dispatcher.forward(request, response);
    }

    protected void redirect(String url) throws IOException {
        response.sendRedirect(url);
    }
}

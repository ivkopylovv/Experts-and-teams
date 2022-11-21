package ru.rsreu.expertsandteams.command;

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

    private static final String prefix = "boba";

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

    protected void forward(String target) throws ServletException, IOException {
        String pagePath = String.format("/pages/%s.jsp", target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(pagePath);

        response.addHeader("X-Target", getUrl(target));
        dispatcher.forward(request, response);
    }

    protected void redirect(String url) throws IOException {
        response.sendRedirect(url);
    }

    private static String getUrl(String target) {
        return String.format("/%s/%s", prefix, target);
    }
}

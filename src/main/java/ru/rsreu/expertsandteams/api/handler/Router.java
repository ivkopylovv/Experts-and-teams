package ru.rsreu.expertsandteams.api.handler;

import com.google.gson.Gson;
import ru.rsreu.expertsandteams.constant.ContentType;
import ru.rsreu.expertsandteams.model.enums.Jsp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public abstract class Router {
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

    protected void forward(Jsp jsp) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher(jsp.getJspPath());

        dispatcher.forward(request, response);
    }

    protected void send(Object object) throws IOException {
        send(object, HttpServletResponse.SC_OK);
    }

    protected void send(Object object, int status) throws IOException {
        PrintWriter out = response.getWriter();
        String objectAsString = new Gson().toJson(object);

        response.setContentType(ContentType.JSON);
        response.setStatus(status);
        response.setCharacterEncoding("UTF-8");

        out.print(objectAsString);
        out.flush();
    }

    protected <T> T getBody(Class<T> bodyClass) throws IOException {
        String bodyAsString = request.getReader()
                .lines()
                .collect(Collectors.joining());
        System.out.println(bodyAsString);

        return (T)new Gson().fromJson(bodyAsString, bodyClass);
    }
}

package ru.rsreu.expertsandteams.controller;

import ru.rsreu.expertsandteams.command.ExceptionHandlerCommand;
import ru.rsreu.expertsandteams.command.FrontCommand;
import ru.rsreu.expertsandteams.command.UnknownCommand;
import ru.rsreu.expertsandteams.helper.StringHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);
        command.init(getServletContext(), req, resp);
        command.process();
    }

    /**
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            FrontCommand command = getCommand(req);
            command.init(getServletContext(), req, res);
            command.send();
        } catch (RuntimeException exception) {
            ExceptionHandlerCommand handlerCommand = new ExceptionHandlerCommand();
            handlerCommand.init(getServletContext(), req, res);
            handlerCommand.handleException(exception);
        }
    }

    /**
     * @param request
     * @return
     */
    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            String path = request.getPathInfo();
            String str = String.format(
                    "ru.rsreu.expertsandteams.command.%sCommand",
                    StringHelper.capitalize(path.substring(1))
            );
            Class<?> type = Class.forName(str);
            return type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

}
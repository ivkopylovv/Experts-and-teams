package ru.rsreu.expertsandteams.controller;

import ru.rsreu.expertsandteams.command.FrontCommand;
import ru.rsreu.expertsandteams.handler.ExceptionHandler;
import ru.rsreu.expertsandteams.helper.CommandHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    /**
     * @param req provide request information for HTTP servlets
     * @param res provide response information for HTTP servlets
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        FrontCommand command = CommandHelper.getCommand(req);

        try {
            command.init(getServletContext(), req, res);
            command.process();
        } catch (Exception exception) {
            ExceptionHandler exceptionHandler = new ExceptionHandler();

            exceptionHandler.init(getServletContext(), req, res);
            exceptionHandler.handleException(exception);
        }
    }

    /**
     * @param req provide request information for HTTP servlets
     * @param res provide response information for HTTP servlets
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        FrontCommand command = CommandHelper.getCommand(req);

        try {
            command.init(getServletContext(), req, res);
            command.send();
        } catch (Exception exception) {
            ExceptionHandler exceptionHandler = new ExceptionHandler();

            exceptionHandler.init(getServletContext(), req, res);
            exceptionHandler.handleException(exception);
        }
    }
}
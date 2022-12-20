package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.api.handler.Router;

import javax.servlet.ServletException;
import java.io.IOException;

public abstract class FrontCommand extends Router {
    public void process() throws ServletException, IOException {
    }

    public void send() throws ServletException, IOException {
    }
}

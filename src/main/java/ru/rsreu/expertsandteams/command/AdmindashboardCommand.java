package ru.rsreu.expertsandteams.command;

import javax.servlet.ServletException;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.Routes.ADMIN_DASHBOARD;

public class AdmindashboardCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward(ADMIN_DASHBOARD);
    }
}

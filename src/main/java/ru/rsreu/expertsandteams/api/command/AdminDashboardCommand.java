package ru.rsreu.expertsandteams.api.command;

import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.AdminDashboardMode;
import ru.rsreu.expertsandteams.model.enums.Jsp;
import ru.rsreu.expertsandteams.model.enums.Role;
import ru.rsreu.expertsandteams.service.ServiceFactory;
import ru.rsreu.expertsandteams.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.rsreu.expertsandteams.constant.RequestParams.*;
import static ru.rsreu.expertsandteams.constant.RequestAttribute.*;

public class AdminDashboardCommand extends FrontCommand {
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userService = ServiceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        List<User> users = userService.getAllUsersWithSession();
        User user = (User)request.getUserPrincipal();

        request.setAttribute(USERS, users);
        request.setAttribute(USER, user);

        forward(Jsp.ADMIN_DASHBOARD);
    }

    @Override
    public void send() throws IOException {
        AdminDashboardMode mode = AdminDashboardMode.valueOf(
                request.getParameter(MODE_PARAM).toUpperCase()
        );

        switch (mode) {
            case ADD_USER:
                addUser();
                break;
            case UPDATE_USER:
                updateUser();
                break;
            case DELETE_USERS:
                deleteUsers();
                break;
        }

        // TODO: return new table
    }

    private void deleteUsers() {
        List<Long> userIds = Arrays.stream(request.getParameterValues(USER_ID_PARAM))
                .map(Long::parseLong).collect(Collectors.toList());

        userService.deleteUsers(userIds);
    }

    private void addUser() {
        String name = request.getParameter(NAME_PARAM);
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String[] skills = request.getParameterValues(SKILLS_PARAM);
        Role role = Role.valueOf(
                request.getParameter(ROLE_PARAM).toUpperCase()
        );

//        userService.addUser(name, username, password, skills, role);
    }

    private void updateUser() throws IOException {
        Long id = Long.valueOf(request.getParameter(ID_PARAM));
        String name = request.getParameter(NAME_PARAM);
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        Boolean isBlocked = Boolean.parseBoolean(request.getParameter(IS_BLOCKED_PARAM));

//        userService.updateUser(id, name, username, password, isBlocked);
    }
}

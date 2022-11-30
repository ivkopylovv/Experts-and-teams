package ru.rsreu.expertsandteams.command;

import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.ExpertSkillDAO;
import ru.rsreu.expertsandteams.database.dao.RoleDAO;
import ru.rsreu.expertsandteams.database.dao.UserDAO;
import ru.rsreu.expertsandteams.exception.RoleNotFoundException;
import ru.rsreu.expertsandteams.exception.UserAlreadyExistsException;
import ru.rsreu.expertsandteams.mapper.SkillMapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.rsreu.expertsandteams.constant.Routes.*;
import static ru.rsreu.expertsandteams.enums.RoleType.EXPERT;
import static ru.rsreu.expertsandteams.enums.RoleType.USER;

public class SignupCommand extends FrontCommand {
    private static final String NAME_PARAM = "name";
    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";
    private static final String SKILLS_PARAM = "skill";

    private UserDAO userDAO;

    private RoleDAO roleDAO;
    private ExpertSkillDAO expertSkillDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userDAO = DAOFactory.getUserDAO();
        roleDAO = DAOFactory.getRoleDAO();
        expertSkillDAO = DAOFactory.getExpertSkillDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        forward(SIGNUP);
    }

    @Override
    public void send() throws ServletException, IOException {
        String name = request.getParameter(NAME_PARAM);
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String[] skills = request.getParameterValues(SKILLS_PARAM);

        boolean userIsExpert = skills != null;

        User user = new User(
            name,
            username,
            password
        );

        userDAO.save(user).orElseThrow(UserAlreadyExistsException::new);
        String roleName;

        if (userIsExpert) {
            roleName = EXPERT.getRole();

            user.setSkills(SkillMapper.mapSkills(user.getId(), skills));
            expertSkillDAO.saveAll(user);
        } else {
            roleName = USER.getRole();
        }

        Role role = roleDAO.findByName(roleName).orElseThrow(RoleNotFoundException::new);

        userDAO.addRoleToUser(user, role);

        redirect(SIGNIN);
    }
}

package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.data.Session;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.dao.*;
import ru.rsreu.expertsandteams.enums.RoleType;
import ru.rsreu.expertsandteams.exception.CredentialsException;
import ru.rsreu.expertsandteams.exception.RoleNotFoundException;
import ru.rsreu.expertsandteams.exception.UserAlreadyExistsException;
import ru.rsreu.expertsandteams.exception.UserEditingException;
import ru.rsreu.expertsandteams.helper.UserHelper;
import ru.rsreu.expertsandteams.mapper.SkillMapper;
import ru.rsreu.expertsandteams.service.UserService;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.List;

import static ru.rsreu.expertsandteams.constant.SessionOptions.SESSION_TIME_LIVE;
import static ru.rsreu.expertsandteams.enums.RoleType.EXPERT;

public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl instance;

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private SessionDAO sessionDAO;
    private ExpertSkillDAO expertSkillDAO;

    private UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, SessionDAO sessionDAO, ExpertSkillDAO expertSkillDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.sessionDAO = sessionDAO;
        this.expertSkillDAO = expertSkillDAO;
    }

    public void updateUser(
            Long id,
            String name,
            String username,
            String password,
            Boolean isBlocked
    ) {
        User user = new User(
                id,
                name,
                username,
                password,
                isBlocked
        );

        userDAO.update(user).orElseThrow(UserEditingException::new);
    }

    public void addUser(
            String name,
            String username,
            String password,
            String[] skills,
            RoleType roleType
    ) {
        User user = new User(
                name,
                username,
                password
        );

        userDAO.save(user).orElseThrow(UserAlreadyExistsException::new);

        if (roleType.equals(EXPERT)) {
            user.setSkills(SkillMapper.mapSkills(user.getId(), skills));
            expertSkillDAO.saveAll(user);
        }

        Role role = roleDAO.findByName(roleType.getRole()).orElseThrow(RoleNotFoundException::new);

        userDAO.addRoleToUser(user, role);
    }

    public void deleteUsers(List<Long> userIds) {
        userDAO.delete(userIds);
    }

    public void changeBlockStatus(List<Long> userIds) {
        userDAO.changeBlockStatus(userIds);
    }

    public List<User> getAllWithoutAdmins() {
        return userDAO.findAllWithoutAdmins();
    }

    public User createSession(String username, String password) {
        User user = this.userDAO.findByUsername(username).orElseThrow(CredentialsException::new);

        if (user.getBlocked() || !user.getPassword().equals(password)) {
            throw new CredentialsException();
        }

        Session session = new Session(user.getId(), new Date(System.currentTimeMillis() + SESSION_TIME_LIVE));
        sessionDAO.save(session);

        return user;
    }

    public void deleteSession(User user) {
        sessionDAO.deleteByUserId(user.getId());
    }

    public List<User> getAllUsersWithSession() {
        return userDAO.findAllWithSession();
    }

    public static UserServiceImpl getInstance() {
        synchronized (UserServiceImpl.class) {
            if (instance == null) {
                instance = new UserServiceImpl(
                        DAOFactory.getUserDAO(),
                        DAOFactory.getRoleDAO(),
                        DAOFactory.getSessionDAO(),
                        DAOFactory.getExpertSkillDAO()
                );
            }
        }

        return instance;
    }
}

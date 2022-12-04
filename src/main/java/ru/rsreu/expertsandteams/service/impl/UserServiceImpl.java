package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.ExpertSkillDAO;
import ru.rsreu.expertsandteams.database.dao.RoleDAO;
import ru.rsreu.expertsandteams.database.dao.UserDAO;
import ru.rsreu.expertsandteams.enums.RoleType;
import ru.rsreu.expertsandteams.exception.RoleNotFoundException;
import ru.rsreu.expertsandteams.exception.UserAlreadyExistsException;
import ru.rsreu.expertsandteams.exception.UserEditingException;
import ru.rsreu.expertsandteams.mapper.SkillMapper;
import ru.rsreu.expertsandteams.service.UserService;

import java.util.List;

import static ru.rsreu.expertsandteams.enums.RoleType.EXPERT;
import static ru.rsreu.expertsandteams.enums.RoleType.USER;

public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl instance;

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private ExpertSkillDAO expertSkillDAO;

    private UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, ExpertSkillDAO expertSkillDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
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

    public List<User> getAllUsersWithSession() {
        return userDAO.findAllWithSession();
    }

    public static UserServiceImpl getInstance() {
        synchronized (UserServiceImpl.class) {
            if (instance == null) {
                instance = new UserServiceImpl(
                        DAOFactory.getUserDAO(),
                        DAOFactory.getRoleDAO(),
                        DAOFactory.getExpertSkillDAO()
                );
            }
        }

        return instance;
    }
}

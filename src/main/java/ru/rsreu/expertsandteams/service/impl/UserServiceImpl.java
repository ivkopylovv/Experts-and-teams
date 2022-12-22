package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.model.api.request.*;
import ru.rsreu.expertsandteams.model.api.response.RoleResponse;
import ru.rsreu.expertsandteams.model.api.response.UserResponse;
import ru.rsreu.expertsandteams.model.entity.ExpertSkill;
import ru.rsreu.expertsandteams.model.entity.Session;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.database.dao.*;
import ru.rsreu.expertsandteams.model.enums.Role;
import ru.rsreu.expertsandteams.model.error.CredentialsException;
import ru.rsreu.expertsandteams.model.error.UserAlreadyExistsException;
import ru.rsreu.expertsandteams.model.error.UserNotFoundException;
import ru.rsreu.expertsandteams.service.UserService;
import ru.rsreu.expertsandteams.support.mapper.RoleMapper;
import ru.rsreu.expertsandteams.support.mapper.UserMapper;

import java.util.Date;
import java.util.List;

import static ru.rsreu.expertsandteams.constant.SessionOptions.SESSION_TIME_LIVE;

public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl instance;

    private final UserDAO userDAO;
    private final SessionDAO sessionDAO;
    private final ExpertSkillDAO expertSkillDAO;

    private UserServiceImpl(UserDAO userDAO, SessionDAO sessionDAO, ExpertSkillDAO expertSkillDAO) {
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
        this.expertSkillDAO = expertSkillDAO;
    }

    @Override
    public void logout(User user) {
    }

    @Override
    public List<UserResponse> getModerDashboardUsers() {
        return List.of();
    }

    @Override
    public List<UserResponse> getAdminDashboardUsers() {
        return List.of();
    }

    @Override
    public void addUser(AddUserRequest addUserRequest) {
        if (userDAO.findByUsername(addUserRequest.getUsername()).isPresent())
            throw new UserAlreadyExistsException();

        User user = UserMapper.mapToUser(addUserRequest);

        saveUser(user, addUserRequest.getSkills());
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        if (userDAO.findByUsername(signUpRequest.getUsername()).isPresent())
            throw new UserAlreadyExistsException();

        User user = UserMapper.mapToUser(signUpRequest);

        saveUser(user, signUpRequest.getSkills());
    }

    @Override
    public RoleResponse signIn(SignInRequest signInRequest) {
        User user = userDAO.findByUsername(signInRequest.getUsername())
                .orElseThrow(CredentialsException::new);

        if (user.getBlocked() || !user.getPassword().equals(signInRequest.getPassword())) {
            throw new CredentialsException();
        }

        Session session = new Session(new Date(System.currentTimeMillis() + SESSION_TIME_LIVE), user);
        sessionDAO.save(session);

        return RoleMapper.mapToRoleResponse(user.getRole().getName());
    }

    @Override
    public void deleteUser(DeleteUserRequest deleteUserRequest) {
        userDAO.deleteById(deleteUserRequest.getUserId());
    }

    @Override
    public void editUser(EditUserRequest editUserRequest) {
        if (userDAO.findByUsername(editUserRequest.getUsername()).isPresent())
            throw new UserAlreadyExistsException();


        User user = UserMapper.mapToUser(editUserRequest);

        userDAO.update(user);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id)
                .orElseThrow(UserNotFoundException::new);
}

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void changeBlockStatus(BlockUsersRequest blockUsersRequest) {
        for (Long userId : blockUsersRequest.getUserIds()) {
            User user = findById(userId);
            userDAO.changeBlockStatus(user);
        }
    }

    public void deleteSession(User user) {
        sessionDAO.deleteByUserId(user.getId());
    }

    private void saveUser(User user, String[] skills) {
        userDAO.save(user);

        if (user.getRole().equals(Role.EXPERT.getName())) {
            for (String skill : skills) {
                ExpertSkill expertSkill = new ExpertSkill(user, skill);
                expertSkillDAO.save(expertSkill);
            }
        }
    }

    public static UserServiceImpl getInstance() {
        synchronized (UserServiceImpl.class) {
            if (instance == null) {
                instance = new UserServiceImpl(
                        DAOFactory.getUserDAO(),
                        DAOFactory.getSessionDAO(),
                        DAOFactory.getExpertSkillDAO()
                );
            }
        }

        return instance;
    }
}

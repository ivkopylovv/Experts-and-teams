package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.model.api.request.SignInRequest;
import ru.rsreu.expertsandteams.model.entity.Session;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.database.dao.*;
import ru.rsreu.expertsandteams.model.error.CredentialsException;
import ru.rsreu.expertsandteams.service.UserService;

import java.util.Date;
import java.util.List;

import static ru.rsreu.expertsandteams.constant.SessionOptions.SESSION_TIME_LIVE;

public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl instance;

    private UserDAO userDAO;
    private SessionDAO sessionDAO;
    private ExpertSkillDAO expertSkillDAO;

    private UserServiceImpl(UserDAO userDAO, SessionDAO sessionDAO, ExpertSkillDAO expertSkillDAO) {
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
        this.expertSkillDAO = expertSkillDAO;
    }
//
//    public void updateUser(
//            Long id,
//            String name,
//            String username,
//            String password,
//            Boolean isBlocked
//    ) {
//        User user = new User(
//                id,
//                name,
//                username,
//                password,
//                isBlocked
//        );
//
//        userDAO.update(user).orElseThrow(UserEditingException::new);
//    }

//    public void addUser(
//            String name,
//            String username,
//            String password,
//            String[] skills,
//            Role roleType
//    ) {
//        User user = new User(
//                name,
//                username,
//                password
//        );
//
//        userDAO.save(user).orElseThrow(UserAlreadyExistsException::new);
//
//        if (roleType.equals(EXPERT)) {
//            user.setSkills(SkillMapper.mapSkills(user.getId(), skills));
//            expertSkillDAO.saveAll(user);
//        }
//
//        Role role = roleDAO.findByName(roleType.getRole()).orElseThrow(RoleNotFoundException::new);
//
//        userDAO.addRoleToUser(user, role);
//    }

    public void deleteUsers(List<Long> userIds) {
        userDAO.delete(userIds);
    }

    public void changeBlockStatus(List<Long> userIds) {
        userDAO.changeBlockStatus(userIds);
    }

    public User createSession(SignInRequest signInRequest) {
        User user = this.userDAO.findByUsername(signInRequest.getUsername()).orElseThrow(CredentialsException::new);

        if (user.getBlocked() || !user.getPassword().equals(signInRequest.getPassword())) {
            throw new CredentialsException();
        }

        Session session = new Session(user, new Date(System.currentTimeMillis() + SESSION_TIME_LIVE));
        sessionDAO.save(session);

        return user;
    }

    public void deleteSession(User user) {
        sessionDAO.deleteByUserId(user.getId());
    }

    public List<User> getAllUsersWithSession() {
        return List.of();
//        return userDAO.findAllWithSession();
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

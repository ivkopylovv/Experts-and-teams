package ru.rsreu.expertsandteams.database.dao.impl;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.data.Skill;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.database.dao.ExpertSkillDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExpertSkillDAOImpl implements ExpertSkillDAO {
    private static volatile ExpertSkillDAOImpl instance;
    private final Resourcer resourcer;

    private ExpertSkillDAOImpl() {
        resourcer = ProjectResourcer.getInstance();
    }

    public void saveAll(User user) {
        String query = resourcer.getString("expertskill.query.save");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            for (Skill skill : user.getSkills()) {
                statement.setLong(1, user.getId());
                statement.setString(2, skill.getName());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ExpertSkillDAOImpl getInstance() {
        synchronized (ExpertSkillDAOImpl.class) {
            if (instance == null) {
                instance = new ExpertSkillDAOImpl();
            }
        }

        return instance;
    }
}

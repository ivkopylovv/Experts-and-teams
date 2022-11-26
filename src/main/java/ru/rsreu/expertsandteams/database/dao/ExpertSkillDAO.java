package ru.rsreu.expertsandteams.database.dao;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.data.Skill;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExpertSkillDAO {
    private static volatile ExpertSkillDAO instance;
    private final Resourcer resourcer;

    private ExpertSkillDAO() {
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

    public static ExpertSkillDAO getInstance() {
        synchronized (ExpertSkillDAO.class) {
            if (instance == null) {
                instance = new ExpertSkillDAO();
            }
        }

        return instance;
    }
}

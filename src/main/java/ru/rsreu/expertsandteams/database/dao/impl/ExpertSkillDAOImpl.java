package ru.rsreu.expertsandteams.database.dao.impl;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.database.dao.AbstractDAO;
import ru.rsreu.expertsandteams.model.entity.ExpertSkill;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.database.dao.ExpertSkillDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExpertSkillDAOImpl extends AbstractDAO implements ExpertSkillDAO {
    private static volatile ExpertSkillDAOImpl instance;

    public static ExpertSkillDAOImpl getInstance() {
        synchronized (ExpertSkillDAOImpl.class) {
            if (instance == null) {
                instance = new ExpertSkillDAOImpl();
            }
        }

        return instance;
    }

    @Override
    public void save(ExpertSkill expertSkill) {
        String query = resourcer.getString("expert.skill.query.save");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, expertSkill.getUser().getId());
            st.setString(2, expertSkill.getSkill());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

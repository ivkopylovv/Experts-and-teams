package ru.rsreu.expertsandteams.database.dao.impl;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.database.dao.AbstractDAO;
import ru.rsreu.expertsandteams.model.entity.ExpertSkill;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.database.dao.ExpertSkillDAO;
import ru.rsreu.expertsandteams.support.mapper.DAOMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpertSkillDAOImpl extends AbstractDAO implements ExpertSkillDAO {
    private static volatile ExpertSkillDAOImpl instance;

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

    @Override
    public List<ExpertSkill> findByExpertId(Long expertId) {
        String query = resourcer.getString("expert.skill.query.find.by.expert.id");
        List<ExpertSkill> expertSkills = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, expertId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                expertSkills.add(DAOMapper.mapToExpertSkill(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expertSkills;
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

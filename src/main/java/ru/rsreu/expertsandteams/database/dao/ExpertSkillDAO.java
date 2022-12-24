package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.ExpertSkill;

import java.util.List;

/**
 * The Data Access Object that providing access
 * to ExpertSkill Entity.
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 *
 */
public interface ExpertSkillDAO {
    /**
     * Saves ExpertSkill Entity to DB
     *
     * @param expertSkill required ExpertSkill Entity to save
     */
    void save(ExpertSkill expertSkill);

    /**
     * Finds for ExpertSkill Entities by expertID
     *
     * @param expertId ID of required User Entity
     * @return list of found ExpertSkill objects
     */
    List<ExpertSkill> findByExpertId(Long expertId);
}

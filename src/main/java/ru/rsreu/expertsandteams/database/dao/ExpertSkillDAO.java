package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.model.entity.ExpertSkill;

import java.util.List;

public interface ExpertSkillDAO {
    void save(ExpertSkill expertSkill);

    List<ExpertSkill> findByExpertId(Long expertId);
}

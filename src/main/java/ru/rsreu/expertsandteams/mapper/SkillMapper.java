package ru.rsreu.expertsandteams.mapper;

import ru.rsreu.expertsandteams.data.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillMapper {
    public static List<Skill> mapSkills(Long userId, String[] skills) {
        List<Skill> userSkills = new ArrayList<>();

        for (String skillName : skills) {
            userSkills.add(new Skill(userId, skillName));
        }

        return userSkills;
    }
}

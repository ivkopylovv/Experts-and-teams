package ru.rsreu.expertsandteams.model.entity;

/**
 * Class that reflects the representation of the
 * ExpertSkill Entity in the DB in the ORM style
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 */
public class ExpertSkill {
    /**
     * User with Role - Expert
     * (ManyToOne relationship to {@link User})
     */
    private User expert;

    /**
     * The skill by which an Expert is selected
     * for consulting
     */
    private String skill;

    public ExpertSkill(String skill) {
        this.skill = skill;
    }

    public ExpertSkill(User expert, String skill) {
        this.expert = expert;
        this.skill = skill;
    }

    /**
     * {@link ExpertSkill#expert}
     */
    public User getExpert() {
        return expert;
    }

    /**
     * {@link ExpertSkill#expert}
     */
    public void setExpert(User expert) {
        this.expert = expert;
    }

    /**
     * {@link ExpertSkill#skill}
     */
    public String getSkill() {
        return skill;
    }

    /**
     * {@link ExpertSkill#skill}
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }
}

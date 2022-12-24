package ru.rsreu.expertsandteams.model.entity;

/**
 * Class that reflects the representation of the
 * ExpertSkill Entity in the DB in the ORM style
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 */
public class ExpertDetail {
    /**
     * User with Role - Expert
     * (OneToOne relationship to {@link User})
     */
    private User expert;

    /**
     * The current count of teams that the Expert advises
     */
    private Long currentTeamsCount;

    /**
     * The maximum count of teams that the Expert can advise
     */
    private Long maxTeamsCount;

    public ExpertDetail(User expert) {
        this.expert = expert;
    }

    /**
     * {@link ExpertDetail#expert}
     */
    public User getExpert() {
        return expert;
    }

    /**
     * {@link ExpertDetail#expert}
     */
    public void setExpert(User expert) {
        this.expert = expert;
    }

    /**
     * {@link ExpertDetail#currentTeamsCount}
     */
    public Long getCurrentTeamsCount() {
        return currentTeamsCount;
    }

    /**
     * {@link ExpertDetail#currentTeamsCount}
     */
    public void setCurrentTeamsCount(Long currentTeamsCount) {
        this.currentTeamsCount = currentTeamsCount;
    }

    /**
     * {@link ExpertDetail#maxTeamsCount}
     */
    public Long getMaxTeamsCount() {
        return maxTeamsCount;
    }

    /**
     * {@link ExpertDetail#maxTeamsCount}
     */
    public void setMaxTeamsCount(Long maxTeamsCount) {
        this.maxTeamsCount = maxTeamsCount;
    }
}

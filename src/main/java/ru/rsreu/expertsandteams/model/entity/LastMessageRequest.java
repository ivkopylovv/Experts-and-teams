package ru.rsreu.expertsandteams.model.entity;

import java.util.Date;

/**
 * Class that reflects the representation of the
 * LastMessageRequest Entity in the DB in the ORM style
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 */
public class LastMessageRequest {
    /**
     * The Team for which the request was made
     * (ManyToOne relationship to {@link Team})
     */
    private Team team;

    /**
     * The User for whom the request was made
     * (ManyToOne relationship to {@link User})
     */
    private User user;

    /**
     * Date the request was saved
     */
    private Date requestDate;

    /**
     * Constructor for class {@link LastMessageRequest}
     *
     * @param team {@link LastMessageRequest#team}
     * @param user {@link LastMessageRequest#user}
     * @param requestDate {@link LastMessageRequest#requestDate}
     */
    public LastMessageRequest(Team team, User user, Date requestDate) {
        this.team = team;
        this.user = user;
        this.requestDate = requestDate;
    }

    /**
     * {@link LastMessageRequest#team}
     */
    public Team getTeam() {
        return team;
    }

    /**
     * {@link LastMessageRequest#team}
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * {@link LastMessageRequest#user}
     */
    public User getUser() {
        return user;
    }

    /**
     * {@link LastMessageRequest#user}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * {@link LastMessageRequest#requestDate}
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * {@link LastMessageRequest#requestDate}
     */
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}

package ru.rsreu.expertsandteams.model.entity;

import java.util.Date;

/**
 * Class that reflects the representation of the
 * TeamMessage Entity in the DB in the ORM style
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 */
public class TeamMessage {
    /**
     * Unique identifier
     */
    private Long id;

    /**
     * The Team in whose chat the message was sent
     * (ManyToOne relationship to {@link Team})
     */
    private Team team;

    /**
     * The Team Member who sends the message
     * (ManyToOne relationship to {@link User})
     */
    private User user;

    /**
     * Sent a message to the chat
     */
    private String message;

    /**
     * Date of the chat message sent
     */
    private Date messageDate;

    /**
     * A User with the Role - Expert to whom
     * the message is addressed
     * (ManyToOne relationship to {@link User})
     */
    private User expert;

    /**
     * Constructor for class {@link TeamMessage}
     *
     * @param team {@link TeamMessage#team}
     * @param user {@link TeamMessage#user}
     * @param message {@link TeamMessage#message}
     * @param expert {@link TeamMessage#expert}
     */
    public TeamMessage(Team team, User user, String message, User expert) {
        this.team = team;
        this.user = user;
        this.message = message;
        this.expert = expert;
    }

    /**
     * Constructor for class {@link TeamMessage}
     *
     * @param id {@link TeamMessage#id}
     * @param team {@link TeamMessage#team}
     * @param user {@link TeamMessage#user}
     * @param message {@link TeamMessage#message}
     * @param messageDate {@link TeamMessage#messageDate}
     * @param expert {@link TeamMessage#expert}
     */
    public TeamMessage(Long id, Team team, User user, String message, Date messageDate, User expert) {
        this.id = id;
        this.team = team;
        this.user = user;
        this.message = message;
        this.messageDate = messageDate;
        this.expert = expert;
    }

    /**
     * {@link TeamMessage#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * {@link TeamMessage#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@link TeamMessage#team}
     */
    public Team getTeam() {
        return team;
    }

    /**
     * {@link TeamMessage#team}
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * {@link TeamMessage#user}
     */
    public User getUser() {
        return user;
    }

    /**
     * {@link TeamMessage#user}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * {@link TeamMessage#message}
     */
    public String getMessage() {
        return message;
    }

    /**
     * {@link TeamMessage#message}
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * {@link TeamMessage#messageDate}
     */
    public Date getMessageDate() {
        return messageDate;
    }

    /**
     * {@link TeamMessage#messageDate}
     */
    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    /**
     * {@link TeamMessage#expert}
     */
    public User getExpert() {
        return expert;
    }

    /**
     * {@link TeamMessage#expert}
     */
    public void setExpert(User expert) {
        this.expert = expert;
    }
}

package ru.rsreu.expertsandteams.model.entity;

import java.util.Date;

/**
 * Class that reflects the representation of the
 * Notification Entity in the DB in the ORM style
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 */
public class Notification {
    /**
     * Unique identifier
     */
    private Long id;

    /**
     * The User for whom the notification was created
     * (ManyToOne relationship to {@link User})
     */
    private User user;

    /**
     * Notification content
     */
    private String message;

    /**
     * Date of creation
     */
    private Date notificationDate;

    /**
     * Constructor for class {@link Notification}
     *
     * @param id {@link Notification#id}
     * @param user {@link Notification#user}
     * @param message {@link Notification#message}
     * @param notificationDate {@link Notification#notificationDate}
     */
    public Notification(Long id, User user, String message, Date notificationDate) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.notificationDate = notificationDate;
    }

    /**
     * Constructor for class {@link Notification}
     *
     * @param user {@link Notification#user}
     * @param message {@link Notification#message}
     */
    public Notification(User user, String message) {
        this.user = user;
        this.message = message;
    }

    /**
     * {@link Notification#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * {@link Notification#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@link Notification#user}
     */
    public User getUser() {
        return user;
    }

    /**
     * {@link Notification#user}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * {@link Notification#message}
     */
    public String getMessage() {
        return message;
    }

    /**
     * {@link Notification#message}
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * {@link Notification#notificationDate}
     */
    public Date getNotificationDate() {
        return notificationDate;
    }

    /**
     * {@link Notification#notificationDate}
     */
    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }
}

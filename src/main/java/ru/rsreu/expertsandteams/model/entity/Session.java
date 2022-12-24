package ru.rsreu.expertsandteams.model.entity;

import java.util.Date;

/**
 * Class that reflects the representation of the
 * Session Entity in the DB in the ORM style
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 */
public class Session {
    /**
     * Unique identifier
     */
    private Long id;

    /**
     * Session expiration time
     */
    private Date expiredAt;

    /**
     * User who has a session in the system
     * (OneToOne relationship to {@link User})
     */
    private User user;

    /**
     * Constructor for class {@link Session}
     *
     * @param expiredAt {@link Session#expiredAt}
     * @param user {@link Session#user}
     */
    public Session(Date expiredAt, User user) {
        this.expiredAt = expiredAt;
        this.user = user;
    }

    /**
     * {@link Session#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * {@link Session#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@link Session#expiredAt}
     */
    public Date getExpiredAt() {
        return expiredAt;
    }

    /**
     * {@link Session#expiredAt}
     */
    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    /**
     * {@link Session#user}
     */
    public User getUser() {
        return user;
    }

    /**
     * {@link Session#user}
     */
    public void setUser(User user) {
        this.user = user;
    }
}

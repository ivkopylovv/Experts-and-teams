package ru.rsreu.expertsandteams.model.entity;

/**
 * Class that reflects the representation of the
 * TeamJoinRequest Entity in the DB in the ORM style
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 */
public class TeamJoinRequest {
    /**
     * Unique identifier
     */
    private Long id;

    /**
     * The User who requests permission to join
     * the Team (ManyToOne relationship to {@link User})
     */
    private User user;

    /**
     * The team to which the User sends a request to join
     * (ManyToOne relationship to {@link Team})
     */
    private Team team;

    /**
     * Attached message when requesting to join the team
     */
    private String message;

    /**
     * Constructor for class {@link TeamJoinRequest}
     *
     * @param id {@link TeamJoinRequest#id}
     * @param user {@link TeamJoinRequest#user}
     * @param message {@link TeamJoinRequest#message}
     */
    public TeamJoinRequest(Long id, User user, String message) {
        this.id = id;
        this.user = user;
        this.message = message;
    }

    /**
     * Constructor for class {@link TeamJoinRequest}
     *
     * @param user {@link TeamJoinRequest#user}
     * @param team {@link TeamJoinRequest#team}
     * @param message {@link TeamJoinRequest#message}
     */
    public TeamJoinRequest(User user, Team team, String message) {
        this.user = user;
        this.team = team;
        this.message = message;
    }

    /**
     * Constructor for class {@link TeamJoinRequest}
     *
     * @param id {@link TeamJoinRequest#id}
     * @param user {@link TeamJoinRequest#user}
     * @param team {@link TeamJoinRequest#team}
     * @param message {@link TeamJoinRequest#message}
     */
    public TeamJoinRequest(Long id, User user, Team team, String message) {
        this.id = id;
        this.user = user;
        this.team = team;
        this.message = message;
    }

    /**
     * {@link TeamJoinRequest#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * {@link TeamJoinRequest#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@link TeamJoinRequest#user}
     */
    public User getUser() {
        return user;
    }

    /**
     * {@link TeamJoinRequest#user}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * {@link TeamJoinRequest#team}
     */
    public Team getTeam() {
        return team;
    }

    /**
     * {@link TeamJoinRequest#team}
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * {@link TeamJoinRequest#message}
     */
    public String getMessage() {
        return message;
    }

    /**
     * {@link TeamJoinRequest#message}
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

package ru.rsreu.expertsandteams.model.entity;

import java.util.List;

/**
 * Class that reflects the representation of the
 * Team Entity in the DB in the ORM style
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 */
public class Team {
    /**
     * Unique identifier
     */
    private Long id;

    /**
     * Common name
     */
    private String name;

    /**
     * Members count
     */
    private Long membersCount;

    /**
     * Team Captain (ManyToOne relationship to {@link User})
     */
    private User captain;

    /**
     * List of Team Members (ManyToMany relationship to {@link User})
     */
    private List<User> members;

    /**
     * Constructor for class {@link Team}
     *
     * @param id {@link Team#id}
     */
    public Team(Long id) {
        this.id = id;
    }

    /**
     * Constructor for class {@link Team}
     *
     * @param id {@link Team#id}
     * @param name {@link Team#name}
     */
    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor for class {@link Team}
     *
     * @param name {@link Team#name}
     * @param captain {@link Team#captain}
     */
    public Team(String name, User captain) {
        this.name = name;
        this.captain = captain;
    }

    /**
     * Constructor for class {@link Team}
     *
     * @param id {@link Team#id}
     * @param name {@link Team#name}
     * @param membersCount {@link Team#membersCount}
     */
    public Team(Long id, String name, Long membersCount) {
        this.id = id;
        this.name = name;
        this.membersCount = membersCount;
    }

    /**
     * Constructor for class {@link Team}
     *
     * @param name {@link Team#name}
     * @param membersCount {@link Team#membersCount}
     * @param captain {@link Team#captain}
     */
    public Team(String name, Long membersCount, User captain) {
        this.name = name;
        this.membersCount = membersCount;
        this.captain = captain;
    }

    /**
     * Constructor for class {@link Team}
     *
     * @param id {@link Team#id}
     * @param name {@link Team#name}
     * @param membersCount {@link Team#membersCount}
     * @param captain {@link Team#captain}
     */
    public Team(Long id, String name, Long membersCount, User captain) {
        this.id = id;
        this.name = name;
        this.membersCount = membersCount;
        this.captain = captain;
    }

    /**
     * {@link Team#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * {@link Team#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@link Team#name}
     */
    public String getName() {
        return name;
    }

    /**
     * {@link Team#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@link Team#membersCount}
     */
    public Long getMembersCount() {
        return membersCount;
    }

    /**
     * {@link Team#membersCount}
     */
    public void setMembersCount(Long membersCount) {
        this.membersCount = membersCount;
    }

    /**
     * {@link Team#captain}
     */
    public User getCaptain() {
        return captain;
    }

    /**
     * {@link Team#captain}
     */
    public void setCaptain(User captain) {
        this.captain = captain;
    }

    /**
     * {@link Team#members}
     */
    public List<User> getMembers() {
        return members;
    }

    /**
     * {@link Team#members}
     */
    public void setMembers(List<User> members) {
        this.members = members;
    }
}

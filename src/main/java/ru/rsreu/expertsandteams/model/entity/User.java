package ru.rsreu.expertsandteams.model.entity;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Class that reflects the representation of the
 * User Entity in the DB in the ORM style
 *
 * @author Ivan Kopylov
 * @author Mikhail Proskuryakov
 * @version 1.0
 */
public class User implements Principal {
    /**
     * Unique identifier
     */
    private Long id;

    /**
     * Common name
     */
    private String name;

    /**
     * Unique username
     */
    private String username;

    /**
     * Common password
     */
    private String password;

    /**
     * Flag indicating whether the User is blocked
     * in the system or not
     */
    private Boolean isBlocked;

    /**
     * Role of the User that determines his capabilities
     * in the system
     */
    private String role;

    /**
     * A set of skills of the User if he has
     * the role of an Expert or an Empty one
     */
    private List<String> skills;

    public User() {}

    /**
     * Constructor for class {@link User}
     *
     * @param id {@link User#id}
     */
    public User(Long id) {
        this.id = id;
    }

    /**
     * Constructor for class {@link User}
     *
     * @param name {@link User#name}
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Constructor for class {@link User}
     *
     * @param id {@link User#id}
     * @param name {@link User#name}
     */
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor for class {@link User}
     *
     * @param id {@link User#id}
     * @param name {@link User#name}
     * @param username {@link User#username}
     * @param password {@link User#password}
     */
    public User(Long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor for class {@link User}
     *
     * @param name {@link User#name}
     * @param username {@link User#username}
     * @param password {@link User#password}
     * @param role {@link User#role}
     */
    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructor for class {@link User}
     *
     * @param id {@link User#id}
     * @param name {@link User#name}
     * @param username {@link User#username}
     * @param password {@link User#password}
     * @param isBlocked {@link User#isBlocked}
     * @param role {@link User#role}
     */
    public User(Long id, String name, String username, String password, Boolean isBlocked, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
        this.role = role;
    }

    /**
     * Constructor for class {@link User}
     *
     * @param name {@link User#name}
     * @param username {@link User#username}
     * @param password {@link User#password}
     * @param isBlocked {@link User#isBlocked}
     * @param role {@link User#role}
     */
    public User(String name, String username, String password, Boolean isBlocked, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
        this.role = role;
    }

    /**
     * {@link User#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * {@link User#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@link User#name}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@link User#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@link User#username}
     */
    public String getUsername() {
        return username;
    }

    /**
     * {@link User#username}
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * {@link User#password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * {@link User#password}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@link User#isBlocked}
     */
    public Boolean isBlocked() {
        return isBlocked;
    }

    /**
     * {@link User#isBlocked}
     */
    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    /**
     * {@link User#role}
     */
    public String getRole() {
        return role;
    }

    /**
     * {@link User#role}
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * {@link User#skills}
     */
    public List<String> getSkills() {
        return skills;
    }
    /**
     * {@link User#skills}
     */
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    /**
     * Checks whether the User is online
     *
     * @param expiredAt session expiration date of the User
     * @return online User status
     */
    public boolean isOnline(Date expiredAt) {
        return expiredAt != null && expiredAt.after(new Date(System.currentTimeMillis()));
    }
}

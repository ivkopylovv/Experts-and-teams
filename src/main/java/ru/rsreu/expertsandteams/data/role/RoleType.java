package ru.rsreu.expertsandteams.data.role;

import ru.rsreu.expertsandteams.database.dao.DAOFactory;

public enum RoleType {
    USER(1),
    EXPERT(2),
    MODERATOR(3),
    ADMIN(4);

    private final int id;

    RoleType(int id) {
        this.id = id;
    }

    public Role getRole() {
        return getRole(id);
    }

    public static Role getRole(int id) {
        return DAOFactory.getInstance().getRoleDAO().getById(id);
    }
}

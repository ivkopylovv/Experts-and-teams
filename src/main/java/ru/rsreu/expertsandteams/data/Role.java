package ru.rsreu.expertsandteams.data;

import java.io.Serializable;

public class Role implements Serializable {
    private Long id;
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

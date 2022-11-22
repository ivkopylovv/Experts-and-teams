package ru.rsreu.expertsandteams.container;

import ru.rsreu.expertsandteams.data.User;

public class RedirectContainer {
    private String url;

    private User user;
    private String error;

    public RedirectContainer(String url, User user, String error) {
        this.url = url;
        this.error = error;
        this.user = user;
    }

    public RedirectContainer(String url, User user) {
        this.url = url;
        this.user = user;
    }

    public RedirectContainer(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

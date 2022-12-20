package ru.rsreu.expertsandteams.model.api.response;

public class RedirectResponse {
    private String url;

    public RedirectResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

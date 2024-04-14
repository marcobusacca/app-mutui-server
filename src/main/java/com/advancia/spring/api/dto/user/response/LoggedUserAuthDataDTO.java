package com.advancia.spring.api.dto.user.response;

import com.advancia.spring.auth.db.pojo.User;

public class LoggedUserAuthDataDTO {

    private String token;
    private Long tokenExpiration;
    private User user;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTokenExpiration() {
        return this.tokenExpiration;
    }

    public void setTokenExpiration(Long tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
                " token='" + getToken() + "'" +
                ", tokenExpiration='" + getTokenExpiration() + "'" +
                ", user='" + getUser() + "'" +
                "}";
    }

}

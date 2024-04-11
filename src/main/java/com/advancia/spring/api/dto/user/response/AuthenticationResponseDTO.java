package com.advancia.spring.api.dto.user.response;

public class AuthenticationResponseDTO {

    private String token;

    public AuthenticationResponseDTO(String token) {
        setToken(token);
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

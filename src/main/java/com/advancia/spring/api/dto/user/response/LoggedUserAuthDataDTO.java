package com.advancia.spring.api.dto.user.response;

import java.util.Date;

public class LoggedUserAuthDataDTO {

    private String token;
    private Long tokenExpiration;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Date dataDiNascita;

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

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDataDiNascita() {
        return this.dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    @Override
    public String toString() {
        return "{" +
                " token='" + getToken() + "'" +
                ", tokenExpiration='" + getTokenExpiration() + "'" +
                ", nome='" + getNome() + "'" +
                ", cognome='" + getCognome() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", dataDiNascita='" + getDataDiNascita() + "'" +
                "}";
    }
}

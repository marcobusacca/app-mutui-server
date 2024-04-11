package com.advancia.spring.api.dto.user.auth;

import java.sql.Date;

import com.advancia.spring.auth.db.pojo.Role;

public class UserSignUpDataDTO {

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Date dataDiNascita;
    private Role ruolo;

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

    public Role getRuolo() {
        return this.ruolo;
    }

    public void setRuolo(Role ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "{" +
                " nome='" + getNome() + "'" +
                ", cognome='" + getCognome() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", dataDiNascita='" + getDataDiNascita() + "'" +
                ", ruolo='" + getRuolo() + "'" +
                "}";
    }
}

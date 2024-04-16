package com.advancia.spring.auth.db.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserAudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, length = 50000000)
    private byte[] picByte;

    @JsonIgnore
    @OneToOne(mappedBy = "userAudio")
    private User user;

    public UserAudio() {
    }

    public UserAudio(String nome, String type, byte[] picByte) {
        setNome(nome);
        setType(type);
        setPicByte(picByte);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return this.picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
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
                " id='" + getId() + "'" +
                ", nome='" + getNome() + "'" +
                ", type='" + getType() + "'" +
                ", picByte='" + getPicByte() + "'" +
                ", user='" + getUser() + "'" +
                "}";
    }
}

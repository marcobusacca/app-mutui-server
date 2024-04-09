package com.advancia.spring.db.configuration.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String codice;
    private String descrizione;

    public Prodotto(String codice, String descrizione) {
        setCodice(codice);
        setDescrizione(descrizione);
    }

    public String getCodice() {
        return this.codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "{" +
                " codice='" + getCodice() + "'" +
                ", descrizione='" + getDescrizione() + "'" +
                "}";
    }
}
package com.advancia.spring.db.configuration.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Listino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String codiceProdotto;
    private double ltv;
    private int durata;
    private double tasso;

    public Listino(String codiceProdotto, double ltv, int durata, double tasso) {
        setCodiceProdotto(codiceProdotto);
        setLtv(ltv);
        setDurata(durata);
        setTasso(tasso);
    }

    public String getCodiceProdotto() {
        return this.codiceProdotto;
    }

    public void setCodiceProdotto(String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }

    public double getLtv() {
        return this.ltv;
    }

    public void setLtv(double ltv) {
        this.ltv = ltv;
    }

    public int getDurata() {
        return this.durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public double getTasso() {
        return this.tasso;
    }

    public void setTasso(double tasso) {
        this.tasso = tasso;
    }

    @Override
    public String toString() {
        return "{" +
                " codiceProdotto='" + getCodiceProdotto() + "'" +
                ", ltv='" + getLtv() + "'" +
                ", durata='" + getDurata() + "'" +
                ", tasso='" + getTasso() + "'" +
                "}";
    }
}
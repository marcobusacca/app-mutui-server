package com.advancia.spring.db.configuration.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Campi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String codiceProdotto;
    private String input;

    public Campi(String codiceProdotto, String input) {
        setCodiceProdotto(codiceProdotto);
        setInput(input);
    }

    public String getCodiceProdotto() {
        return this.codiceProdotto;
    }

    public void setCodiceProdotto(String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "{" +
                " codiceProdotto='" + getCodiceProdotto() + "'" +
                ", input='" + getInput() + "'" +
                "}";
    }
}
package com.advancia.spring.db.configuration.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VincoliProdotti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String codiceProdotto;
    private int eta;
    private boolean cliente;

    public VincoliProdotti(String codiceProdotto, int eta, boolean cliente) {
        setCodiceProdotto(codiceProdotto);
        setEta(eta);
        setCliente(cliente);
    }

    public String getCodiceProdotto() {
        return this.codiceProdotto;
    }

    public void setCodiceProdotto(String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }

    public int getEta() {
        return this.eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public boolean isCliente() {
        return this.cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "{" +
                " prodotto='" + getCodiceProdotto() + "'" +
                ", eta='" + getEta() + "'" +
                ", cliente='" + isCliente() + "'" +
                "}";
    }
}
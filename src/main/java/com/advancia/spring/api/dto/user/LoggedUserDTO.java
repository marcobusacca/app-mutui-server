package com.advancia.spring.api.dto.user;

public class LoggedUserDTO {

    private int eta;
    private boolean cliente;

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "{" +
                "'eta': " + getEta() + ", " +
                "'cliente': " + isCliente() +
                '}';
    }
}
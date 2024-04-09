package com.advancia.spring.api.dto.user.form;

public class LoggedUserFormDTO {

    private double importo;
    private double richiesto;
    private int durata;
    private double reddito;
    private double costoRistrutturazione;
    private String classeEnergetica;

    public double getImporto() {
        return this.importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public double getRichiesto() {
        return this.richiesto;
    }

    public void setRichiesto(double richiesto) {
        this.richiesto = richiesto;
    }

    public int getDurata() {
        return this.durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public double getReddito() {
        return this.reddito;
    }

    public void setReddito(double reddito) {
        this.reddito = reddito;
    }

    public double getCostoRistrutturazione() {
        return this.costoRistrutturazione;
    }

    public void setCostoRistrutturazione(double costoRistrutturazione) {
        this.costoRistrutturazione = costoRistrutturazione;
    }

    public String getClasseEnergetica() {
        return this.classeEnergetica;
    }

    public void setClasseEnergetica(String classeEnergetica) {
        this.classeEnergetica = classeEnergetica;
    }

    @Override
    public String toString() {
        return "{" +
                " importo='" + getImporto() + "'" +
                ", richiesto='" + getRichiesto() + "'" +
                ", durata='" + getDurata() + "'" +
                ", reddito='" + getReddito() + "'" +
                ", costoRistrutturazione='" + getCostoRistrutturazione() + "'" +
                ", classeEnergetica='" + getClasseEnergetica() + "'" +
                "}";
    }
}

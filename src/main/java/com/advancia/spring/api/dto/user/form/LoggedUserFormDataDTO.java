package com.advancia.spring.api.dto.user.form;

import com.advancia.spring.db.configuration.pojo.Prodotto;

public class LoggedUserFormDataDTO {
    private Prodotto prodottoSelezionato;
    private LoggedUserFormDTO loggedUserForm;

    public Prodotto getProdottoSelezionato() {
        return this.prodottoSelezionato;
    }

    public void setProdottoSelezionato(Prodotto prodottoSelezionato) {
        this.prodottoSelezionato = prodottoSelezionato;
    }

    public LoggedUserFormDTO getLoggedUserForm() {
        return this.loggedUserForm;
    }

    public void setLoggedUserForm(LoggedUserFormDTO loggedUserForm) {
        this.loggedUserForm = loggedUserForm;
    }

    @Override
    public String toString() {
        return "{" +
                " prodottoSelezionato='" + getProdottoSelezionato() + "'" +
                ", loggedUserForm='" + getLoggedUserForm() + "'" +
                "}";
    }
}

package com.advancia.spring.api.dto.user.response;

public class AuthenticationResponseDTO {

    private boolean esito;
    private String messaggio;
    private String token;

    public boolean isEsito() {
        return this.esito;
    }

    public boolean getEsito() {
        return this.esito;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    public String getMessaggio() {
        return this.messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" +
                " esito='" + isEsito() + "'" +
                ", messaggio='" + getMessaggio() + "'" +
                ", token='" + getToken() + "'" +
                "}";
    }
}

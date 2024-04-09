package com.advancia.spring.api.dto.user.response;

public class LoggedUserResponseDTO {

    private Object response;
    private boolean esito;
    private String messaggio;

    public Object getResponse() {
        return this.response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public boolean isEsito() {
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

    @Override
    public String toString() {
        return "{" +
                "'response': " + getResponse() + ", " +
                "'esito': " + isEsito() + ", " +
                "'messaggio': " + getMessaggio() +
                '}';
    }
}

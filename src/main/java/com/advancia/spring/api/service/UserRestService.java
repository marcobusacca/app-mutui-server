package com.advancia.spring.api.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advancia.spring.api.dao.UserRestDAO;
import com.advancia.spring.api.dto.user.LoggedUserDTO;
import com.advancia.spring.api.dto.user.form.LoggedUserFormDataDTO;
import com.advancia.spring.api.dto.user.response.LoggedUserResponseDTO;
import com.advancia.spring.db.configuration.pojo.Prodotto;

@Service
public class UserRestService {

    @Autowired
    private UserRestDAO userRestDAO;

    public LoggedUserResponseDTO getLoggedUserProducts(LoggedUserDTO loggedUserDTO) {

        LoggedUserResponseDTO responseDTO = new LoggedUserResponseDTO();

        if (loggedUserDTO.getEta() < 18) {
            responseDTO.setResponse(new HashMap<>());
            responseDTO.setEsito(false);
            responseDTO.setMessaggio("Non è possibile richiedere un mutuo se hai un'età inferiore a 18 anni");
        } else {
            try {
                List<Prodotto> prodottiDisponibili = userRestDAO.getAvailableProducts(loggedUserDTO);
                responseDTO.setResponse(prodottiDisponibili);
                responseDTO.setEsito(true);
                responseDTO.setMessaggio("");

            } catch (SQLException e) {
                responseDTO.setResponse(new HashMap<>());
                responseDTO.setEsito(false);
                responseDTO.setMessaggio("Errore di connessione. Riprovare più tardi.");
                e.printStackTrace();
            }
        }

        return responseDTO;
    }

    public LoggedUserResponseDTO getLoggedUserForm(String codiceProdottoSelezionato) {

        LoggedUserResponseDTO responseDTO = new LoggedUserResponseDTO();

        try {
            List<String> campiInput = userRestDAO.getCampiInputByCodiceProdotto(codiceProdottoSelezionato);
            responseDTO.setResponse(campiInput);
            responseDTO.setEsito(true);
            responseDTO.setMessaggio("");
        } catch (Exception e) {
            responseDTO.setResponse(new HashMap<>());
            responseDTO.setEsito(false);
            responseDTO.setMessaggio("Errore di connessione. Riprovare più tardi.");
            e.printStackTrace();
        }
        return responseDTO;
    }

    public LoggedUserResponseDTO getLoggedUserFinalResult(LoggedUserFormDataDTO loggedUserFormDataDTO) {

        LoggedUserResponseDTO responseDTO = new LoggedUserResponseDTO();

        try {
            Map<String, Double> response = userRestDAO.getLoggedUserFinalResult(loggedUserFormDataDTO);
            if (response.isEmpty()) {
                responseDTO.setResponse(new HashMap<>());
                responseDTO.setEsito(false);
                responseDTO.setMessaggio("Nessun risultato trovato");
            } else {
                responseDTO.setResponse(response);
                responseDTO.setEsito(true);
                responseDTO.setMessaggio("");
            }
        } catch (Exception e) {
            responseDTO.setResponse(new HashMap<>());
            responseDTO.setEsito(false);
            responseDTO.setMessaggio("Errore di connessione. Riprovare più tardi.");
            e.printStackTrace();
        }
        return responseDTO;
    }
}

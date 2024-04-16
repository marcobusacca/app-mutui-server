package com.advancia.spring.api.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.advancia.spring.api.dao.UserRestDAO;
import com.advancia.spring.api.dto.user.UserDataDTO;
import com.advancia.spring.api.dto.user.auth.UserLoginDataDTO;
import com.advancia.spring.api.dto.user.auth.UserSignUpDataDTO;
import com.advancia.spring.api.dto.user.form.LoggedUserFormDataDTO;
import com.advancia.spring.api.dto.user.response.LoggedUserAuthDataDTO;
import com.advancia.spring.api.dto.user.response.LoggedUserResponseDTO;
import com.advancia.spring.auth.db.service.UserService;
import com.advancia.spring.db.configuration.pojo.Prodotto;

@Service
public class UserRestService {

    @Autowired
    private UserRestDAO userRestDAO;

    @Autowired
    private UserService userService;

    public LoggedUserResponseDTO signUp(
            UserSignUpDataDTO userSignUpDataDTO,
            MultipartFile userImageFile,
            MultipartFile userAudioFile) {

        LoggedUserResponseDTO responseDTO = new LoggedUserResponseDTO();

        if (userService.loadUserByUsername(userSignUpDataDTO.getEmail()) != null) {
            responseDTO.setResponse(new HashMap<>());
            responseDTO.setEsito(false);
            responseDTO.setMessaggio("Questa e-mail è già presente nel nostro sistema");
            return responseDTO;
        }

        try {
            LoggedUserAuthDataDTO authDataDTO = userRestDAO.signUp(userSignUpDataDTO, userImageFile, userAudioFile);
            responseDTO.setResponse(authDataDTO);
            responseDTO.setEsito(true);
            responseDTO.setMessaggio("");
        } catch (IOException e) {
            responseDTO.setResponse(new HashMap<>());
            responseDTO.setEsito(false);
            responseDTO.setMessaggio("Errore di connessione. Riprovare più tardi.");
        }

        return responseDTO;
    }

    public LoggedUserResponseDTO login(UserLoginDataDTO userLoginDataDTO) {

        LoggedUserResponseDTO responseDTO = new LoggedUserResponseDTO();

        try {
            LoggedUserAuthDataDTO authDataDTO = userRestDAO.login(userLoginDataDTO);
            responseDTO.setResponse(authDataDTO);
            responseDTO.setEsito(true);
            responseDTO.setMessaggio("");

        } catch (AuthenticationException e) {
            responseDTO.setResponse(new HashMap<>());
            responseDTO.setEsito(false);
            responseDTO.setMessaggio("Credenziali non valide");
        }

        return responseDTO;
    }

    public LoggedUserResponseDTO getLoggedUserProducts(UserDataDTO userDataDTO) {

        LoggedUserResponseDTO responseDTO = new LoggedUserResponseDTO();

        if (userDataDTO.getEta() < 18) {
            responseDTO.setResponse(new HashMap<>());
            responseDTO.setEsito(false);
            responseDTO.setMessaggio("Non è possibile richiedere un mutuo se hai un'età inferiore a 18 anni");
        } else {
            try {
                List<Prodotto> prodottiDisponibili = userRestDAO.getAvailableProducts(userDataDTO);
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

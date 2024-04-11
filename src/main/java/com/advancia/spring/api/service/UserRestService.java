package com.advancia.spring.api.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.advancia.spring.api.dao.UserRestDAO;
import com.advancia.spring.api.dto.user.UserDataDTO;
import com.advancia.spring.api.dto.user.form.LoggedUserFormDataDTO;
import com.advancia.spring.api.dto.user.response.AuthenticationResponseDTO;
import com.advancia.spring.api.dto.user.response.LoggedUserResponseDTO;
import com.advancia.spring.auth.db.pojo.User;
import com.advancia.spring.auth.db.service.UserService;
import com.advancia.spring.auth.service.JwtService;
import com.advancia.spring.db.configuration.pojo.Prodotto;

@Service
public class UserRestService {

    @Autowired
    private UserRestDAO userRestDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO signUp(User request) {

        User user = new User(
                request.getNome(),
                request.getCognome(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getDataDiNascita());

        userService.save(user);

        String token = jwtService.generateToken(user);

        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO(token);

        return responseDTO;
    }

    public AuthenticationResponseDTO login(User request) {

        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()));

        User user = userService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);

        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO(token);

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

package com.advancia.spring.api.restcontroller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.advancia.spring.api.dto.user.UserDataDTO;
import com.advancia.spring.api.dto.user.form.LoggedUserFormDataDTO;
import com.advancia.spring.api.dto.user.response.AuthenticationResponseDTO;
import com.advancia.spring.api.dto.user.response.LoggedUserResponseDTO;
import com.advancia.spring.api.service.UserRestService;
import com.advancia.spring.auth.db.pojo.User;

@RestController
@RequestMapping("/api")
// @CrossOrigin({ "http://localhost:5173", "http://localhost:4200" })
public class UserRestController {

    @Autowired
    private UserRestService userRestService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> signUp(@RequestBody User request) {
        AuthenticationResponseDTO responseDTO = userRestService.signUp(request);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody User request) {
        AuthenticationResponseDTO responseDTO = userRestService.login(request);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/logged-user-products")
    public ResponseEntity<LoggedUserResponseDTO> getLoggedUserProducts(@RequestBody UserDataDTO userDataDTO) {
        LoggedUserResponseDTO responseDTO = userRestService.getLoggedUserProducts(userDataDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/logged-user-form")
    public ResponseEntity<LoggedUserResponseDTO> getLoggedUserForm(@RequestBody String codiceProdottoSelezionato) {
        LoggedUserResponseDTO responseDTO = userRestService.getLoggedUserForm(codiceProdottoSelezionato);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/logged-user-final-result")
    public ResponseEntity<LoggedUserResponseDTO> getLoggedUserFinalResult(
            @RequestBody LoggedUserFormDataDTO loggedUserFormDataDTO) {
        LoggedUserResponseDTO responseDTO = userRestService.getLoggedUserFinalResult(loggedUserFormDataDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
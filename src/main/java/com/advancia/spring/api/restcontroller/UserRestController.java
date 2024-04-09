package com.advancia.spring.api.restcontroller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.advancia.spring.api.dto.user.LoggedUserDTO;
import com.advancia.spring.api.dto.user.form.LoggedUserFormDataDTO;
import com.advancia.spring.api.dto.user.response.LoggedUserResponseDTO;
import com.advancia.spring.api.service.UserRestService;

@RestController
@RequestMapping("/api")
@CrossOrigin({ "http://localhost:5173", "http://localhost:4200" })
public class UserRestController {

    @Autowired
    private UserRestService userRestService;

    @PostMapping("/logged-user-products")
    public ResponseEntity<LoggedUserResponseDTO> getLoggedUserProducts(@RequestBody LoggedUserDTO loggedUserDTO) {
        LoggedUserResponseDTO responseDTO = userRestService.getLoggedUserProducts(loggedUserDTO);
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
package com.medicine.online_doctor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicine.online_doctor.DTO.AuthenticationDTO;
import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.service.AuthenticationService;

@RestController
public class AuthenticationController {

    private AuthenticationService authenticationService;

    

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginDetails> register(@RequestBody LoginDetails loginDetails){
        return authenticationService.addUser(loginDetails);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTO> login(@RequestBody LoginDetails loginDetails){
        return authenticationService.login(loginDetails);
    }

}

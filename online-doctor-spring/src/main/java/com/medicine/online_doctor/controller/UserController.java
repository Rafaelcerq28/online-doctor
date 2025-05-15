package com.medicine.online_doctor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.medicine.online_doctor.model.Patient;
import com.medicine.online_doctor.model.User;
import com.medicine.online_doctor.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Logic to create a patient
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return userService.createPatient(patient,token);

    }

}

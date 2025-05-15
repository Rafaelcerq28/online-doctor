package com.medicine.online_doctor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.medicine.online_doctor.model.Doctor;
import com.medicine.online_doctor.service.DoctorService;

@RestController
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return doctorService.createDoctor(doctor, token);
    }
}

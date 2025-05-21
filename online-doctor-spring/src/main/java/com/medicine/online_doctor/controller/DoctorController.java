package com.medicine.online_doctor.controller;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/doctor")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Doctor> getDoctorById(@PathVariable(value = "id") Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/doctor/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Doctor> getDoctorByUsername(@PathVariable(value = "username") String username) {
        return doctorService.getDoctorByUsername(username);
    }

    @PutMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.OK) 
    public ResponseEntity<Doctor> updateDoctor(@PathVariable(value = "id") Long id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteDoctor(@PathVariable(value = "id") Long id) {
        return doctorService.deleteDoctor(id);
    }

}

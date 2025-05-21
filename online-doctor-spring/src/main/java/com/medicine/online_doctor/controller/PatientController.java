package com.medicine.online_doctor.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.medicine.online_doctor.model.Patient;
import com.medicine.online_doctor.service.PatientService;

@RestController
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //Logic to create a patient
    @PostMapping("/patient")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return patientService.createPatient(patient,token);
    }

    @GetMapping("/patient")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Patient> getPatientById(@PathVariable(value = "id") Long id) {
        System.out.println("value of id no controller: " + id);
        return patientService.getPatientById(id);
    }

    @GetMapping("/patient/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Patient> getPatientByUsername(@PathVariable(value = "username") String username) {
        return patientService.getPatientByUsername(username);
    }

    @PutMapping("/patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") Long id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("/patient/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePatient(@PathVariable(value="id") Long id) {
        return patientService.deletePatient(id);
    }
}

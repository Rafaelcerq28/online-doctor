package com.medicine.online_doctor.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;

import com.medicine.online_doctor.model.MedicalAppointment;
import com.medicine.online_doctor.service.MedicalAppointmentService;

@RestController
public class MedicalAppointmentController {

    private MedicalAppointmentService medicalAppointmentService;

    public MedicalAppointmentController(MedicalAppointmentService medicalAppointmentService) {
        this.medicalAppointmentService = medicalAppointmentService;
    }

    @PostMapping("/appointments")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<MedicalAppointment> createAppointment() {
        // Logic to create a medical appointment
        // This will typically involve validating the request, saving the appointment to the database,
        // and returning the created appointment as an EntityModel.
        // return medicalAppointmentService.createAppointment();
        return null;
    }

}

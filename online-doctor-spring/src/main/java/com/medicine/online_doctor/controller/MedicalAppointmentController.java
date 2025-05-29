package com.medicine.online_doctor.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @PostMapping("/appointments/{availabilityId}/doctor/{doctorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<MedicalAppointment> createAppointment(@RequestHeader("Authorization") String authHeader, @PathVariable(value = "doctorId") Long doctorId, @PathVariable(value = "availabilityId") Long availabilityId) {
        String token = authHeader.replace("Bearer ", "");
        return medicalAppointmentService.createAppointment(doctorId, availabilityId, token);
    }

}

package com.medicine.online_doctor.controller;

import java.time.LocalDate;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.medicine.online_doctor.model.Availability;
import com.medicine.online_doctor.service.AvailabilityService;

@RestController
public class AvailabilityController {

    AvailabilityService availabilityService;

    // Constructor injection for AvailabilityService
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping("/availability")
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Availability> createAvailability(@RequestBody Availability availability, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return availabilityService.createAvailability(availability, token);
    }

    @GetMapping("/availability")
    @ResponseStatus(HttpStatus.OK)
    public List<Availability> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }

    @GetMapping("/availability/{specialisation}/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<Availability> getAvailabilitiesBySpecialisation(@PathVariable(value = "specialisation") String specialisation, @PathVariable(value = "date") LocalDate date) {

        return availabilityService.getAvailabilitiesBySpecialisation(specialisation,date);
    }
    
    @DeleteMapping("/availability/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAvailability(@PathVariable(value = "id") Long id) {
        availabilityService.deleteAvailability(id);
    }

    //availability by doctor
    @GetMapping("/availability/doctor/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Availability> getAvailabilitiesByDoctor(@PathVariable(value = "doctorId") Long doctorId) {
        return availabilityService.getAvailabilitiesByDoctor(doctorId);
    }
}

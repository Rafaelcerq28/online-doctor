package com.medicine.online_doctor.service;

import java.lang.foreign.Linker.Option;
import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.medicine.online_doctor.exeptions.UserAlreadyExistException;
import com.medicine.online_doctor.exeptions.UserNotFoundException;
import com.medicine.online_doctor.model.Doctor;
import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.model.Roles;
import com.medicine.online_doctor.repository.DoctorRepository;
import com.medicine.online_doctor.repository.LoginDetailsRepository;
import com.medicine.online_doctor.service.security.JWTService;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private LoginDetailsRepository loginDetailsRepository;
    private JWTService jwtService;

    

    public DoctorService(DoctorRepository doctorRepository, LoginDetailsRepository loginDetailsRepository,
            JWTService jwtService) {
        this.doctorRepository = doctorRepository;
        this.loginDetailsRepository = loginDetailsRepository;
        this.jwtService = jwtService;
    }



    public ResponseEntity<Doctor> createDoctor(Doctor doctor, String token) {
        String username = jwtService.extractUserName(token);

        //check if the user already exists in the database
        Optional<LoginDetails> userToSearch = loginDetailsRepository.findByUsername(username);

        if (userToSearch.isPresent() == false) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        Optional<Doctor> doctorToSearch = doctorRepository.findByEmail(doctor.getEmail());
        if (doctorToSearch.isPresent()) {
            throw new UserAlreadyExistException("Doctor already exists with email: " + doctor.getEmail());
        }

        //set the login details for the doctor and active to true
        userToSearch.get().setRole(Roles.ROLE_DOCTOR);
        doctor.setLoginDetails(userToSearch.get());
        doctor.setActive(true);

        Doctor storedDoctor = doctorRepository.save(doctor);

        //inserir o endpoint com a localização (/doctor/id)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(storedDoctor.getId())
                .toUri();

        return ResponseEntity.created(location).body(storedDoctor);
    }

}

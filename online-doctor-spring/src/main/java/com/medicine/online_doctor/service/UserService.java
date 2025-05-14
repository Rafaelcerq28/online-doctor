package com.medicine.online_doctor.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicine.online_doctor.exeptions.UserAlreadyExistException;
import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.model.Patient;
import com.medicine.online_doctor.repository.DoctorRepository;
import com.medicine.online_doctor.repository.LoginDetailsRepository;
import com.medicine.online_doctor.repository.PatientRepository;
import com.medicine.online_doctor.repository.UserRepository;
import com.medicine.online_doctor.service.security.JWTService;
import com.medicine.online_doctor.exeptions.UserNotFoundException;

@Service
public class UserService {

    
    private UserRepository userRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private LoginDetailsRepository loginDetailsRepository;
    private JWTService jwtService;



    public UserService(UserRepository userRepository, PatientRepository patientRepository,
            DoctorRepository doctorRepository, LoginDetailsRepository loginDetailsRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.loginDetailsRepository = loginDetailsRepository;
        this.jwtService = jwtService;
    }



    public ResponseEntity<Patient> createPatient(Patient patient, String token) {

        String username = jwtService.extractUserName(token);

        //check if the user already exists in the database
        Optional<LoginDetails> userToSearch = loginDetailsRepository.findByUsername(username);

        if (userToSearch.isPresent() == false) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        //set the login details for the patient and active to true
        patient.setLoginDetails(userToSearch.get());
        patient.setActive(true);

        Patient storedPatient = patientRepository.save(patient);

        //inserir o endpoint com a localização (/parient/id)

        return ResponseEntity.ok().body(storedPatient);
    }

}

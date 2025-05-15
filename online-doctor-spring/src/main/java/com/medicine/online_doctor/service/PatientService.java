package com.medicine.online_doctor.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.medicine.online_doctor.exeptions.UserNotFoundException;
import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.model.Patient;
import com.medicine.online_doctor.repository.DoctorRepository;
import com.medicine.online_doctor.repository.LoginDetailsRepository;
import com.medicine.online_doctor.repository.PatientRepository;
import com.medicine.online_doctor.service.security.JWTService;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private LoginDetailsRepository loginDetailsRepository;
    private JWTService jwtService;

    public PatientService(PatientRepository patientRepository, DoctorRepository doctorRepository,
            LoginDetailsRepository loginDetailsRepository, JWTService jwtService) {
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
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(storedPatient.getId())
                .toUri();

        return ResponseEntity.created(location).body(storedPatient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public EntityModel<Patient> getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        //gerar outros metodos para mandar no entity model

        EntityModel<Patient> entityModel = EntityModel.of(patient.get());
        
        return entityModel;
    }

    public ResponseEntity<Patient> updatePatient(Long id, Patient patient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePatient'");
    }

    public ResponseEntity<Void> deletePatient(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}

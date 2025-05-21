package com.medicine.online_doctor.service;

import java.lang.foreign.Linker.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
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

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public EntityModel<Doctor> getDoctorById(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);

        if (doctor.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        EntityModel<Doctor> entityModel = EntityModel.of(doctor.get());

        return entityModel;
    }

    public EntityModel<Doctor> getDoctorByUsername(String username) {
        Optional<LoginDetails> userToSearch = loginDetailsRepository.findByUsername(username);
        Optional<Doctor> doctor = doctorRepository.findByLoginDetails(userToSearch.get());

        if (doctor.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        EntityModel<Doctor> entityModel = EntityModel.of(doctor.get());

        return entityModel;
    }

    public ResponseEntity<Doctor> updateDoctor(Long id, Doctor doctor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDoctor'");
    }

    public ResponseEntity<Void> deleteDoctor(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);

        if (doctor.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        doctorRepository.delete(doctor.get());

        return ResponseEntity.noContent().build();
    }

}

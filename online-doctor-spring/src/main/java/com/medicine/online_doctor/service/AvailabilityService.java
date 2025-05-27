package com.medicine.online_doctor.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.medicine.online_doctor.exeptions.UserNotFoundException;
import com.medicine.online_doctor.model.Availability;
import com.medicine.online_doctor.model.Doctor;
import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.repository.AvailabilityRepository;
import com.medicine.online_doctor.repository.DoctorRepository;
import com.medicine.online_doctor.repository.LoginDetailsRepository;
import com.medicine.online_doctor.service.security.JWTService;

@Service
public class AvailabilityService {

    private LoginDetailsRepository loginDetailsRepository;
    private JWTService jwtService;
    private DoctorRepository doctorRepository;
    private AvailabilityRepository availabilityRepository;

    public AvailabilityService(LoginDetailsRepository loginDetailsRepository, JWTService jwtService,
            DoctorRepository doctorRepository, AvailabilityRepository availabilityRepository) {
        this.loginDetailsRepository = loginDetailsRepository;
        this.jwtService = jwtService;
        this.doctorRepository = doctorRepository;
        this.availabilityRepository = availabilityRepository;
    }

    public EntityModel<Availability> createAvailability(Availability availability, String token) {
        
        String username = jwtService.extractUserName(token);

        //check if the user already exists in the database
        Optional<LoginDetails> userToSearch = loginDetailsRepository.findByUsername(username);

        if (userToSearch.isPresent() == false) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        System.out.println("usuario: " + userToSearch.get().getUsername());

        Optional<Doctor> doctorToSearch = doctorRepository.findByLoginDetails(userToSearch.get());

        System.out.println(availability.toString());

        if (doctorToSearch.isPresent() == false) {
            throw new UserNotFoundException("Doctor not found. ");
        }

        /*
         * - Criar regras tipo:
         * - Verificar se o médico já tem disponibilidade para a mesma data
         * - Verificar se o médico já tem disponibilidade para a mesma hora
         * - verificar se o fim da sessão é maior que o início
         * - configurar para que o fim da sessão seja definido automaticamete, sempre 30 minutos após o início
         * - retornar todas as disponibilidades do médico para a mesma data no hateoas
        */

        availability.setDoctor(doctorToSearch.get());

        availabilityRepository.save(availability);

        EntityModel<Availability> entityModel = EntityModel.of(availability);

        return entityModel;
    }

    public List<Availability> getAllAvailabilities() {

        List<Availability> availabilities = availabilityRepository.findAll();
        if (availabilities.isEmpty()) {
            throw new UserNotFoundException("No availabilities found.");
        }
        return availabilities;
    }

    public List<Availability> getAvailabilitiesBySpecialisation(String specialisation, LocalDate date) {

        specialisation = specialisation.substring(0).toUpperCase();

        List<Availability> availabilities = availabilityRepository.findBySpecialisationNameAndDate(specialisation,date);
        
        if (availabilities.isEmpty()) {
            throw new UserNotFoundException("No availabilities found for specialisation: " + specialisation);
        }
        
        return availabilities;
    }

    public void deleteAvailability(Long id) {
        Optional<Availability> availabilityToDelete = availabilityRepository.findById(id);
        
        if (availabilityToDelete.isEmpty()) {
            throw new UserNotFoundException("Availability not found with id: " + id);
        }
        
        availabilityRepository.delete(availabilityToDelete.get());
    }

    public List<Availability> getAvailabilitiesByDoctor(Long doctorId) {
        Optional<Doctor> doctorToSearch = doctorRepository.findById(doctorId);
        
        if (doctorToSearch.isEmpty()) {
            throw new UserNotFoundException("Doctor not found with id: " + doctorId);
        }
        
        List<Availability> availabilities = availabilityRepository.findByDoctor(doctorToSearch.get());
        
        if (availabilities.isEmpty()) {
            throw new UserNotFoundException("No availabilities found for doctor with id: " + doctorId);
        }
        
        return availabilities;
    }

}

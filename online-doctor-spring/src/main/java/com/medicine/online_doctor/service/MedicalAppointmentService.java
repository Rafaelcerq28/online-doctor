package com.medicine.online_doctor.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.medicine.online_doctor.exeptions.NotAllowedException;
import com.medicine.online_doctor.exeptions.UserNotFoundException;
import com.medicine.online_doctor.model.Availability;
import com.medicine.online_doctor.model.Consultation;
import com.medicine.online_doctor.model.Doctor;
import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.model.MedicalAppointment;
import com.medicine.online_doctor.model.Patient;
import com.medicine.online_doctor.model.Roles;
import com.medicine.online_doctor.repository.AvailabilityRepository;
import com.medicine.online_doctor.repository.ConsultationRepository;
import com.medicine.online_doctor.repository.DoctorRepository;
import com.medicine.online_doctor.repository.LoginDetailsRepository;
import com.medicine.online_doctor.repository.MedicalAppointmentRepository;
import com.medicine.online_doctor.repository.PatientRepository;
import com.medicine.online_doctor.service.security.JWTService;

@Service
public class MedicalAppointmentService {

    private DoctorRepository doctorRepository;
    private MedicalAppointmentRepository medicalAppointmentRepository;
    private JWTService jwtService;
    private LoginDetailsRepository loginDetailsRepository;
    private AvailabilityRepository availabilityRepository;
    private PatientRepository patientRepository;
    private ConsultationRepository consultationRepository;
    
    public MedicalAppointmentService(DoctorRepository doctorRepository,
            MedicalAppointmentRepository medicalAppointmentRepository, JWTService jwtService,
            LoginDetailsRepository loginDetailsRepository, AvailabilityRepository availabilityRepository,
            PatientRepository patientRepository, ConsultationRepository consultationRepository) {
        this.doctorRepository = doctorRepository;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
        this.jwtService = jwtService;
        this.loginDetailsRepository = loginDetailsRepository;
        this.availabilityRepository = availabilityRepository;
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
    }






    public EntityModel<MedicalAppointment> createAppointment(Long doctorId, Long availabilityId, String token) {
        
        String username = jwtService.extractUserName(token);

        //check if the user already exists in the database
        Optional<LoginDetails> userToSearch = loginDetailsRepository.findByUsername(username);

        if (!userToSearch.isPresent() || userToSearch.get().getRole() != Roles.ROLE_PATIENT) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        Optional<Patient> patient = patientRepository.findByLoginDetails(userToSearch.get());

        if (patient.isEmpty()) {
            throw new UserNotFoundException("Patient not found with username: " + username);
        }
        
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);

        if (doctor.isEmpty()) {
            throw new UserNotFoundException("Doctor not found with id: " + doctorId);
        }

        Optional<Availability> availability = availabilityRepository.findById(availabilityId);
        if (availability.isEmpty()) {
            throw new UserNotFoundException("Availability not found with id: " + availabilityId);
        }

        if(availability.get().isAvailable() == false) {
            throw new NotAllowedException("Availability is not available with id: " + availabilityId);
        }
        
        MedicalAppointment medicalAppointment = new MedicalAppointment();
        medicalAppointment.setDoctor(doctor.get());
        medicalAppointment.setPatient(patient.get());
        medicalAppointment.setAvailability(availability.get());

        LocalDateTime dateTime = LocalDateTime.of(
                availability.get().getDate(),
                availability.get().getStart()
        );

        medicalAppointment.setDate(dateTime);

        //delete me later e quando fizer isso mudar para nullable = true
        Consultation consultation = consultationRepository.save(new Consultation());
        
        medicalAppointment.setConsultation(consultation);

        //mudar para enum
        medicalAppointment.setPaymentStatus("PENDING");

        medicalAppointment = medicalAppointmentRepository.save(medicalAppointment);

        EntityModel<MedicalAppointment> entityModel = EntityModel.of(medicalAppointment);

        return entityModel;
    }

}

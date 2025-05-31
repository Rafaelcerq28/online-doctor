package com.medicine.online_doctor.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicine.online_doctor.controller.DoctorController;
import com.medicine.online_doctor.controller.MedicalAppointmentController;
import com.medicine.online_doctor.controller.PatientController;
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

        availability.get().setAvailable(false);
        
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


    //essa loginca precisa ficar melhorada, pois o paciente pode cancelar a consulta e estornar o pagamento
    public ResponseEntity<MedicalAppointment> cancelAppointment(Long doctorId, Long appointmentId, String token) {
        
        String username = jwtService.extractUserName(token);

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

        Optional<MedicalAppointment> medicalAppointment = medicalAppointmentRepository.findById(appointmentId);
        if (medicalAppointment.isEmpty()) {
            throw new UserNotFoundException("Medical appointment not found with id: " + appointmentId);
        }

        Optional<Availability> availability = availabilityRepository.findById(medicalAppointment.get().getAvailability().getId());

        if (availability.isEmpty()) {
            throw new UserNotFoundException("Availability not found with id: " + medicalAppointment.get().getAvailability().getId());
        }

        medicalAppointmentRepository.delete(medicalAppointment.get());
        System.out.println("Medical appointment to cancel: " + medicalAppointment.get().getId());

        Availability availabilityToSave = availability.get();
        availabilityToSave.setAvailable(true);

        System.out.println("Availability to save: " + availabilityToSave.isAvailable());
        availabilityRepository.save(availabilityToSave);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<CollectionModel<EntityModel<MedicalAppointment>>> getUserMedicalAppointments(String token) {
        
        String username = jwtService.extractUserName(token);

        Optional<LoginDetails> userToSearch = loginDetailsRepository.findByUsername(username);

        if (!userToSearch.isPresent() || userToSearch.get().getRole() != Roles.ROLE_PATIENT) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        Optional<Patient> patient = patientRepository.findByLoginDetails(userToSearch.get());
        if (patient.isEmpty()) {
            throw new UserNotFoundException("Patient not found with username: " + username);
        }

        List<MedicalAppointment> medicalAppointments = medicalAppointmentRepository.findByPatientId(patient.get().getId());
        
        if (medicalAppointments.isEmpty()) {
            throw new UserNotFoundException("No medical appointments found for patient with username: " + username);
        }

        // Create a collection model with links for each medical appointment
        List<EntityModel<MedicalAppointment>> entity = new ArrayList<>();
        
        for(MedicalAppointment appointment : medicalAppointments){
            entity.add(EntityModel.of(appointment,
                WebMvcLinkBuilder.linkTo(methodOn(MedicalAppointmentController.class).getUserAppointments(token)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(DoctorController.class).getDoctorById(appointment.getDoctor().getId())).withRel("doctor"),
                WebMvcLinkBuilder.linkTo(methodOn(PatientController.class).getPatientById(appointment.getPatient().getId())).withRel("patient")));
        }

        CollectionModel<EntityModel<MedicalAppointment>> collectionModel = CollectionModel.of(entity);//,
                // WebMvcLinkBuilder.linkTo(methodOn(MedicalAppointmentController.class).getUserAppointments(token)).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }



}

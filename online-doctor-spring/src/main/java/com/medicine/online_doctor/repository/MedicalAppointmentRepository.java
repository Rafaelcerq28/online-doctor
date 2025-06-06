package com.medicine.online_doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicine.online_doctor.model.Availability;
import com.medicine.online_doctor.model.MedicalAppointment;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {

    MedicalAppointment findByAvailabilityId(Long availabilityId);

    List<MedicalAppointment> findByPatientId(Long id);
    
    // Additional query methods can be defined here if needed

}

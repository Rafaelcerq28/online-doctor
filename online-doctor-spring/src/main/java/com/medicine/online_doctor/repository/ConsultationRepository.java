package com.medicine.online_doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicine.online_doctor.model.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    
    // Additional query methods can be defined here if needed

}

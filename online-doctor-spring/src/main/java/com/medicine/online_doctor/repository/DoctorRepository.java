package com.medicine.online_doctor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicine.online_doctor.model.Doctor;
import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.model.Patient;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByLoginDetails(LoginDetails loginDetails);
}

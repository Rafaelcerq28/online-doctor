package com.medicine.online_doctor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicine.online_doctor.model.LoginDetails;

@Repository
public interface LoginDetailsRepository extends JpaRepository<LoginDetails, Long> {
    Optional<LoginDetails> findByUsername(String username);

}

package com.medicine.online_doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicine.online_doctor.model.User;

//Genetic repository, can be used to access both Patient and Doctor entities
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

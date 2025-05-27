package com.medicine.online_doctor.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicine.online_doctor.model.Availability;
import com.medicine.online_doctor.model.Doctor;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

@Query("""
    SELECT a FROM Availability a
    WHERE a.date = :date AND a.doctor.specialisation.name = :specialisationName
""")
List<Availability> findBySpecialisationNameAndDate(@Param("specialisationName") String specialisationName, @Param("date") LocalDate date);

List<Availability> findByDoctor(Doctor doctor);


}

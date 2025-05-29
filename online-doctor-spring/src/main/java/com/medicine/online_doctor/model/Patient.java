package com.medicine.online_doctor.model;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {

    
    @Column(nullable = false)
    private int age;

    private String gender;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MedicalAppointment> medicalHistoy;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Payments> paymentHistory;
    
    @CreationTimestamp
    private Instant createdAt;
    
    public Patient() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<MedicalAppointment> getMedicalHistoy() {
        return medicalHistoy;
    }

    public void setMedicalHistoy(List<MedicalAppointment> medicalHistoy) {
        this.medicalHistoy = medicalHistoy;
    }

    public List<Payments> getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(List<Payments> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    @Override
    public String toString() {
        return "Patient [age=" + age + ", gender=" + gender + ", medicalHistoy=" + medicalHistoy + ", paymentHistory="
                + paymentHistory + "]";
    }





    
    
}


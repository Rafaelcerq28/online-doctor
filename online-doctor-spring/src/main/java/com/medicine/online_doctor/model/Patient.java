package com.medicine.online_doctor.model;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "login_details_id")
    private LoginDetails loginDetails;
    
    @Column(unique=true,nullable = false)
    private String email;
    
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private int age;
    private String nationality;

    private String gender;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String address;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<MedicalAppointment> medicalHistoy;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Payments> paymentHistory;

    @CreationTimestamp
    private Instant createdAt;
    
    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MedicalAppointment> getMedicalHistoy() {
        return medicalHistoy;
    }

    public void setMedicalHistoy(List<MedicalAppointment> medicalHistoy) {
        this.medicalHistoy = medicalHistoy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<Payments> getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(List<Payments> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    
    @Override
    public String toString() {
        return "Patient [id=" + id + ", loginDetails=" + loginDetails + ", email=" + email + ", name=" + name
                + ", surname=" + surname + ", age=" + age + ", nationality=" + nationality + ", gender=" + gender
                + ", phone=" + phone + ", address=" + address + ", medicalHistoy=" + medicalHistoy + ", createdAt="
                + createdAt + "]";
    }


    
    
}


package com.medicine.online_doctor.model;

import java.time.Instant;
import java.util.List;

public class Patient {

    private Long id;
    private LoginDetails loginDetails;
    private String email;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String phone;
    private String address;
    private List<Consultation> medicalHistoy;
    // payment history
    
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

    public List<Consultation> getMedicalHistoy() {
        return medicalHistoy;
    }

    public void setMedicalHistoy(List<Consultation> medicalHistoy) {
        this.medicalHistoy = medicalHistoy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", loginDetails=" + loginDetails + ", email=" + email + ", name=" + name
                + ", surname=" + surname + ", age=" + age + ", gender=" + gender + ", phone=" + phone + ", address="
                + address + ", medicalHistoy=" + medicalHistoy + "]";
    }








    

    
}


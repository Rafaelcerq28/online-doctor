package com.medicine.online_doctor.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class Doctor {

    private Long id;
    private LoginDetails loginDetails;
    private String name;
    private String surname;
    private String specialisation;
    private String phone;
    private String address;
    private String email;
    private Instant createdAt;

    public Doctor() {
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
    public String getSpecialisation() {
        return specialisation;
    }
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "Doctor [id=" + id + ", loginDetails=" + loginDetails + ", name=" + name + ", surname=" + surname
                + ", specialisation=" + specialisation + ", phone=" + phone + ", address=" + address + ", email="
                + email + "]";
    }

    

}

package com.medicine.online_doctor.model;

import java.time.Instant;

public class MedicalAppointment {


    private Long id;
    private Instant date;
    private Doctor doctor;
    private Patient patient;
    private String paymentStatus;

    public MedicalAppointment() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Instant getDate() {
        return date;
    }
    public void setDate(Instant date) {
        this.date = date;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "MedicalAppointments [id=" + id + ", date=" + date + ", doctor=" + doctor + ", patient=" + patient
                + ", paymentStatus=" + paymentStatus + "]";
    }

    

}

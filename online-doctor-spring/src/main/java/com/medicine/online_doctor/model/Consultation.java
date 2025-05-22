package com.medicine.online_doctor.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicalEvaluation;
    private String exams;
    private boolean needReturn;
    private String prescription;
    private String medicalReport;

    @CreationTimestamp
    private Instant createdAt;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicalEvaluation() {
        return medicalEvaluation;
    }
    public void setMedicalEvaluation(String medicalEvaluation) {
        this.medicalEvaluation = medicalEvaluation;
    }
    public String getExams() {
        return exams;
    }
    public void setExams(String exams) {
        this.exams = exams;
    }
    public boolean isNeedReturn() {
        return needReturn;
    }
    public void setNeedReturn(boolean needReturn) {
        this.needReturn = needReturn;
    }
    public String getPrescription() {
        return prescription;
    }
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
    public String getMedicalReport() {
        return medicalReport;
    }
    public void setMedicalReport(String medicalReport) {
        this.medicalReport = medicalReport;
    }

    @Override
    public String toString() {
        return "Consultation [id=" + id +  ", medicalEvaluation="
                + medicalEvaluation + ", exams=" + exams + ", needReturn=" + needReturn + ", prescription="
                + prescription + ", medicalReport=" + medicalReport + "]";
    }

    
    

}

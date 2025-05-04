package com.medicine.online_doctor.model;

public class Consultation {

    private Long id;
    private MedicalAppointment medicalAppointment;
    private String medicalEvaluation;
    private String exams;
    private boolean needReturn;
    private String prescription;
    private String medicalReport;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public MedicalAppointment getMedicalAppointment() {
        return medicalAppointment;
    }
    public void setMedicalAppointment(MedicalAppointment medicalAppointment) {
        this.medicalAppointment = medicalAppointment;
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
        return "Consultation [id=" + id + ", medicalAppointment=" + medicalAppointment + ", medicalEvaluation="
                + medicalEvaluation + ", exams=" + exams + ", needReturn=" + needReturn + ", prescription="
                + prescription + ", medicalReport=" + medicalReport + "]";
    }

    
    

}

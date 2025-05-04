package com.medicine.online_doctor.model;

import java.time.Instant;

public class Specialisations {

    private Long id;
    private String specialisation;
    private Instant createdAt;
    
    public Specialisations() {
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSpecialisation() {
        return specialisation;
    }
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    

}

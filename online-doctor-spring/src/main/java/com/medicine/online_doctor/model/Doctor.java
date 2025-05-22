package com.medicine.online_doctor.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("DOCTOR")
public class Doctor extends User {


    @Column(nullable = false)
    private String registrationCode;

    @Column(nullable = false)
    private double price;

    private double rating;
    private int experience;
    
    @CreationTimestamp
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "specialisation_id")
    private Specialisation Specialisation;

    public Doctor() {
    }


    public String getRegistrationCode() {
        return registrationCode;
    }


    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public double getRating() {
        return rating;
    }


    public void setRating(double rating) {
        this.rating = rating;
    }


    public int getExperience() {
        return experience;
    }


    public void setExperience(int experience) {
        this.experience = experience;
    }




    


    

}

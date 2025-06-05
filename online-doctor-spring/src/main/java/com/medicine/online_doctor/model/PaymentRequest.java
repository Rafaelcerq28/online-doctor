package com.medicine.online_doctor.model;

public class PaymentRequest {
    private Long amount;
    private String email;
    
    public Long getAmount() {
        return amount;
    }
    public String getEmail() {
        return email;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public PaymentRequest() {
    }

    
}

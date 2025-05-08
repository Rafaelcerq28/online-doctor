package com.medicine.online_doctor.DTO;

public record AuthenticationDTO(
    String username,
    String role,
    String token
) {

}

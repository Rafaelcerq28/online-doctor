package com.medicine.online_doctor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.medicine.online_doctor.DTO.AuthenticationDTO;
import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.repository.LoginDetailsRepository;
import com.medicine.online_doctor.service.security.JWTService;

@Service
public class HelloService {
    
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    @Autowired
    private LoginDetailsRepository loginDetailsRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;



}

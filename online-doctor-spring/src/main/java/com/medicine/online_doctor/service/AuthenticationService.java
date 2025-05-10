package com.medicine.online_doctor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.medicine.online_doctor.DTO.AuthenticationDTO;
import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.repository.LoginDetailsRepository;
import com.medicine.online_doctor.service.security.JWTService;

@RestController
public class AuthenticationService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private LoginDetailsRepository loginDetailsRepository;

    private AuthenticationManager authenticationManager;

    private JWTService jwtService;
        
    // Constructor injection for dependencies
    public AuthenticationService(LoginDetailsRepository loginDetailsRepository,
            AuthenticationManager authenticationManager, JWTService jwtService) {
        this.loginDetailsRepository = loginDetailsRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // Method to add a new user
    public ResponseEntity<LoginDetails> addUser(LoginDetails user) {

        Optional<LoginDetails> userToCheck = loginDetailsRepository.findByUsername(user.getUsername());

        if(userToCheck.isPresent() == true){
            return ResponseEntity.badRequest().build();
        }
        
        user.setPassword(encoder.encode(user.getPassword()));
        LoginDetails userToSave = loginDetailsRepository.save(user);
        return ResponseEntity.ok(userToSave);
    }

    // Method to login a user
    public ResponseEntity<AuthenticationDTO> login(LoginDetails user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            // If the user is authenticated, generate a JWT token and send it back to the client
            if(authentication.isAuthenticated()){
                Optional<LoginDetails> authenticatedUser = loginDetailsRepository.findByUsername(user.getUsername());
                
                String username = authenticatedUser.get().getUsername();
                String role = authenticatedUser.get().getRole().name();
                String token = jwtService.generateToken(authenticatedUser.get());

                AuthenticationDTO authDTO = new AuthenticationDTO(username, role, token);

                return ResponseEntity.ok(authDTO);
            }

        return ResponseEntity.status(401).build();
    }
}

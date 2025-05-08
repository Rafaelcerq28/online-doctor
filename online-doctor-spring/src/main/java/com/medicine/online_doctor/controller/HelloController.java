package com.medicine.online_doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicine.online_doctor.DTO.AuthenticationDTO;
import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.service.HelloService;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Doctor!";
    }



}

package com.medicine.online_doctor.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthenticated implements UserDetails{

    LoginDetails loginDetails;

    public UserAuthenticated(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(loginDetails.getRole().name()));
    }

    @Override
    public String getPassword() {
        return loginDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return loginDetails.getUsername();
    }


}

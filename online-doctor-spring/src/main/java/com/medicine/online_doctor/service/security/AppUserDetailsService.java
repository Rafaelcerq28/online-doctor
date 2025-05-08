package com.medicine.online_doctor.service.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medicine.online_doctor.model.LoginDetails;
import com.medicine.online_doctor.model.UserAuthenticated;
import com.medicine.online_doctor.repository.LoginDetailsRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{
    
    private LoginDetailsRepository loginDetailsRepository;



    public AppUserDetailsService(LoginDetailsRepository loginDetailsRepository) {
        this.loginDetailsRepository = loginDetailsRepository;
    }



    //This method is used to load user-specific data
    //It is used by Spring Security to load user-specific data
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<LoginDetails> user = loginDetailsRepository.findByUsername(username);
        //If user is not found, throw an exception
        if(user.isPresent() ==false){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        //Return the UserPrincipal object (which implements UserDetails interface)
        return new UserAuthenticated(user.get());
    }
}

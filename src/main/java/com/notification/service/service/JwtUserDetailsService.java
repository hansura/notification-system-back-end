package com.notification.service.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.notification.service.model.User;
import com.notification.service.model.UserDTO;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
   private  UserServiceRepository userServiceRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          User user = userServiceRepository.findByUsername(username);
           if(user == null){
               throw new UsernameNotFoundException("User Not Found");
           }
        return  new org.springframework.security.core.userdetails.User(user.getUsername() , user.getPassword() , new ArrayList<>());
    }

    public User save (UserDTO user){


        User newUser = new User();

        newUser.setUsername( user.getUsername());
        newUser.setPassword(bcryptEncoder.encode( user.getPassword()));

        return userServiceRepository.save(newUser);
    
    }

}

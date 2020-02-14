package com.notification.service.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.notification.service.config.JwtTokenUtil;
import com.notification.service.model.JwtRequest;
import com.notification.service.model.JwtResponse;
import com.notification.service.model.User;
import com.notification.service.model.UserDTO;
import com.notification.service.service.JwtUserDetailsService;
import com.notification.service.service.UserServiceRepository;

@CrossOrigin({ "http://localhost:4200" , "http://localhost" , "http://10.1.80.66"})
@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserServiceRepository userRepository;
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}


    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
    	
         User users =  userRepository.findByUsername(user.getUsername());
         if(users != null) 
        	 throw new Exception("User Already Exist ");
         
    	 userDetailsService.save(user);
    	 
		return ResponseEntity.ok().body(HttpStatus.OK);
	}


    public void authenticate (String username , String password ) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){

            throw new Exception("User_Disabled" );
            
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials" );
        }


    }



}

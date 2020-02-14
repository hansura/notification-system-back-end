package com.notification.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.service.model.Role;
import com.notification.service.model.User;
import com.notification.service.service.RoleRepositiory;
import com.notification.service.service.UserServiceRepository;

@RestController
@CrossOrigin(  { "http://localhost:4200" , "http://localhost" , "http://10.1.80.66"})
@RequestMapping( value ="admin/")
public class RoleController {
	
	
	@Autowired
	RoleRepositiory roleRepository;
	
	@Autowired
	UserServiceRepository userRepository;
	
	@GetMapping( value ="/{username}/getAllRole")
	public List<Role> getAllRole(@PathVariable( value = "username") String userName) throws Exception {
		
		User user = userRepository.findByUsername(userName);
		
		  if(user == null)
		    throw new Exception("User Not Found ");
		  
		  return roleRepository.findAll();
	
	}
	
	@PostMapping( value ="/{username}/addRole")
	@Transactional
	public Role addRole( @PathVariable( value ="username") String userName , @RequestBody Role role  ) throws Exception {
		
		        User user = userRepository.findByUsername(userName);
		        if(user == null)
		        	throw new Exception("user not found !!!");
		        
		        return roleRepository.save(role);
		        
		
	}
	
	@PutMapping( value ="/{username}/updateRole/{roleId}")
	@Transactional
	public Role updateRole( @PathVariable( value ="username") String userName ,
			@PathVariable( value ="roleId") Long roleId  ,@RequestBody Role role  ) throws Exception {
		
		        User user = userRepository.findByUsername(userName);
		        System.out.println(userName);
		        if(user == null)
		        	throw new Exception("user not found !!!");
		        
		        Role oldRole = roleRepository.findById(roleId).orElseThrow(
		        		   () -> new Exception("Role Not Found !!")
		        		);
		        
		          oldRole.setRoleName(role.getRoleName());
		          oldRole.setRoleDescription(role.getRoleDescription());
		          
		        return roleRepository.save(oldRole);
		        
		
	}
	
	
	@DeleteMapping( value ="/{username}/deleteRole/{roleId}")
	public  Map<String , Boolean> deleteRole(@PathVariable(value ="roleId")  Long roleId)
	 throws Exception
	{
		 
		Map<String, Boolean> response = new HashMap<>();
		
	 Role	role = this.roleRepository.findById(roleId).orElseThrow( 
				
				 () -> new Exception("Role Id not Found ")
				);
	 
	 
	  this.roleRepository.delete(role);
		
       response.put("deleted", true);
       
       return response;
		
	}
	
	
	
	
	

}

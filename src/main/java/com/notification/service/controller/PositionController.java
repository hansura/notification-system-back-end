package com.notification.service.controller;

import java.util.ArrayList;
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

import com.notification.service.model.Position;
import com.notification.service.model.PositionDAO;
import com.notification.service.model.Role;
import com.notification.service.model.User;
import com.notification.service.service.PositionRepository;
import com.notification.service.service.RoleRepositiory;
import com.notification.service.service.UserServiceRepository;

@RestController
@CrossOrigin(  { "http://localhost:4200" , "http://localhost" , "http://10.1.80.66"})
@RequestMapping( value ="admin/")
public class PositionController {

	
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	RoleRepositiory roleRepository;
	
	@Autowired
	UserServiceRepository userRepository;
	
	@GetMapping( value ="{username}/getAllPosition")
	public List<Position> getAllPosition( @PathVariable( value ="username") String userName ) throws Exception{
	
		  User user = userRepository.findByUsername(userName);
		  if( user == null)
			  throw new Exception( " User Not Found ");
		  
		  
		  return positionRepository.findAll();
		  
		  
		
	}
	
	
	@PostMapping( value ="{username}/addPosition")
	@Transactional
	public Position postPosition( @PathVariable( value ="username") String userName , @RequestBody PositionDAO positionDAO) throws Exception{
	
		if(positionDAO.getPosition() != null)
			System.out.println("Position DAO : \t is not null");
		
		     Position position = positionDAO.getPosition();
		     List<Long> roleIds = positionDAO.getRoleIds();
		     List<Role> roles = new ArrayList<Role>();
		     
	           roleIds.forEach(  (id) -> 
	                     roles.add(roleRepository.findById(id).get())
	        		   );
		      
		     
		  
		     
		User user = userRepository.findByUsername(userName);
		  if( user == null)
			  throw new Exception( " User Not Found ");
		  
    	  if( positionDAO.getPosition() != null)
			  System.out.println(positionDAO.getPosition());
		      
		  
                  positionRepository.save(position);
                  System.out.println(roles);
                  
		          position.setRole(roles);
	 	   
		       System.out.println(position);
		       
		      
		       
		       
		  return  positionRepository.save(position);

		  
		  
		
	}
	
	
	@PutMapping( value ="{username}/updatePosition/{positionId}")
	@Transactional
	public Position updatePosition( @PathVariable( value ="username") String userName , 
			 @PathVariable(value ="positionId") Long positionId
			,@RequestBody PositionDAO positionDAO) throws Exception{
	
		
		 Position oldPosition = positionRepository.findById(positionId).orElseThrow(
				  () -> new Exception("Postion Not Found ! ")
				 );
		
		if(positionDAO.getPosition() != null)
			System.out.println("Position DAO : \t is not null");
		
		User user = userRepository.findByUsername(userName);
		  if( user == null)
			  throw new Exception( " User Not Found ");
		  
		     Position position = positionDAO.getPosition();
		    System.out.println(position);
		     
		     List<Long> roleIds = positionDAO.getRoleIds();
		     List<Role> roles = new ArrayList<Role>();
		     
	           roleIds.forEach(  (id) -> 
	                     roles.add(roleRepository.findById(id).get())
	        		   );
		      
		     
		  
		     
		
                 oldPosition.setPositionName(position.getPositionName());
    	          oldPosition.setPositionDescription(position.getPositionDescription());
    	          
                  positionRepository.save(oldPosition);
                  System.out.println(roles);
                  
		          oldPosition.setRole(roles);
	 	   
		       System.out.println(oldPosition);
		       
		      
		       
		       
		  return  positionRepository.save(oldPosition);

		  
		  
		
	}
	
	
	@DeleteMapping(  value ="{username}/deletePosition/{positionId}")
	public Map<String , Boolean> deletePosition( @PathVariable(value ="positionId") Long positionId)
	throws Exception
	{
		
		Map<String , Boolean> response = new HashMap<>();
		
		
		
		Position position = positionRepository.findById(positionId).orElseThrow(
				
				()-> new Exception("Position Id Not Found !")
				);
		
		
		 positionRepository.delete(position);
		
		 response.put("Deleted", true);
		 return response;
		
	}
	
	
	
	
	
	
}

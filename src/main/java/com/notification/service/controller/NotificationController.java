package com.notification.service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notification.service.model.Branch;
import com.notification.service.model.NotificationDAO;
import com.notification.service.model.NotificationManager;
import com.notification.service.model.Position;
import com.notification.service.model.User;
import com.notification.service.service.BranchRepository;
import com.notification.service.service.NotifyRepository;
import com.notification.service.service.PositionRepository;
import com.notification.service.service.UserServiceRepository;

@RestController
@CrossOrigin( { "http://localhost:4200" , "http://localhost" , "http://10.1.80.66"})
@RequestMapping( value ="notification/")
public class NotificationController {
	
	@Autowired
	 NotifyRepository  notifyRepository;

	@Autowired
	UserServiceRepository userServiceRepository;
	
	@Autowired
	BranchRepository branchRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	
	
	
	
	
	
	@GetMapping(value ="/{username}/getAllNotification")
	public Iterable<NotificationManager> getAllNotification (@PathVariable ( value ="username") String username) throws Exception{
		User user = userServiceRepository.findByUsername(username);
        if( user == null ) {
        	throw new Exception("User Not Found ");
        }
        	
		return notifyRepository.findAll();
	}
	
	@GetMapping( value ="{username}/getAllNotification/{notifyId}")
	public ResponseEntity<NotificationManager> getNotificationById(
			@PathVariable( value ="username") String userName , 
			@PathVariable(value ="notifyId") Long notifyId
			) throws Exception{
		
		     User user = userServiceRepository.findByUsername(userName);
		     if(user == null)
		    	 throw new Exception("User Not Found");
		
		     NotificationManager notification = notifyRepository.findById(notifyId).orElseThrow( 
		    		 
		    		 () -> new Exception("Notification Not Found")
		    		 );
		     
		     
		    return ResponseEntity.ok().body(notification);
		
		
		
	}
	
	
	@PutMapping( value ="{username}/getAllNotification/{notifyId}")
	public NotificationManager updateNotification (@PathVariable ( value ="username") String username 
			, @PathVariable (value ="notifyId") Long notifyId, @RequestBody NotificationDAO notification
			) throws Exception{
		
		User user = userServiceRepository.findByUsername(username);
        if( user == null ) {
        	throw new Exception("User Not Found ");
        }
        
        
           Long previousId = notification.getPreviousId();
		   Long currentId = notification.getCurrentId();
		   Long branchId  = notification.getBranchId();
		   Long removedId = notification.getRemovedId();
		   
		   if(branchId == null) {
			   throw new Exception("branch NOT FOUND");
		   }
		  
		    Branch branch = branchRepository.findById(branchId).orElseThrow(
		    		   () -> new Exception("Branch Not Found !!")
		    		);
		    		     
		    		
		    		
             Position previousPosition = positionRepository.findById(previousId).orElseThrow(
            		 () -> new Exception("Position Not Found !!")
            		 );
            		 
             Position currentPosition = positionRepository.findById(currentId).orElseThrow(
            		  () -> new Exception("Position Not Found ! ")
            		 
            		 );
            		 
      		
             Position removedPosition = positionRepository.findById(removedId).orElseThrow(
            		 () -> new Exception("Position Not Found !!")
            		 );  
          
		   

		NotificationManager  notify =  notifyRepository.findById(notifyId).orElseThrow( 	
				() -> new Exception("Notification Not Found")
				);
		
		
		
		
		 NotificationManager updatedNotification = new NotificationManager();
		 if( notify != null) {
			 
			
          		NotificationManager newNotification = notification.getNotification();
          		
          		newNotification.setBranch(branch);
          		newNotification.setCurrentPosition(currentPosition);
          		newNotification.setPreviousPosition(previousPosition);
              	newNotification.setRemovedPosition(removedPosition);	
          		updatedNotification = newNotification;
          		
          		 
			 
		 }
				
				
		return notifyRepository.save(updatedNotification);
		
	}
	
	
	@PostMapping( value ="{username}/createNotification" , consumes = "application/json" , produces = "application/json")
	public NotificationManager notification (
			  @PathVariable ( value ="username") String username 
			 ,@RequestBody NotificationDAO notification 
			) throws Exception {
		
		    NotificationManager postedNotification = notification.getNotification();
		    if(postedNotification == null) 
		    	 throw new Exception("Notification is Null");
		    
		   User user = userServiceRepository.findByUsername(username);
		   
		   
		   
		   Long previousId = notification.getPreviousId();
		   Long currentId = notification.getCurrentId();
		   Long branchId  = notification.getBranchId();
		   Long removedId = notification.getRemovedId();
		   
		   if(user.getUsername() == null ) {
			   
			   throw new Exception("User Not Found");
		   }
		   if(branchId == null) {
			   throw new Exception("branch NOT FOUND");
		   }
		  
		    Branch branch = branchRepository.findById(branchId).orElseThrow(
		    		   () -> new Exception("Branch Not Found !!")
		    		);
		    		     
		    		
		    		
             Position previousPosition = positionRepository.findById(previousId).orElseThrow(
            		 () -> new Exception("Position Not Found !!")
            		 );
            		 
             Position currentPosition = positionRepository.findById(currentId).orElseThrow(
            		  () -> new Exception("Position Not Found ! ")
            		 
            		 );
            		 
             Position removedPosition = positionRepository.findById(removedId).orElseThrow(
            		 () -> new Exception("Position Not Found !!")
            		 );    
               System.out.println(removedPosition);
		    		
             postedNotification.setBranch(branch);
             postedNotification.setCurrentPosition(currentPosition);
             postedNotification.setPreviousPosition(previousPosition);
             postedNotification.setRemovedPosition(removedPosition);
             postedNotification.setMakerId(username);
		   
		   return  notifyRepository.save(postedNotification);
		
				
	}
	
	
	@DeleteMapping( value ="{username}/deleteNotification/{notifyId}")
	public Map<String , Boolean> deleteNotification( @PathVariable( value ="notifyId") Long notificationId)
	throws Exception
	{
		
		   
		  Map<String , Boolean> response = new HashMap<>();
		  
		  NotificationManager notification = notifyRepository.findById(notificationId).orElseThrow(
				  
				   ()-> new Exception("Notification Id Not Found ")
				  
				  );
				  
		notifyRepository.delete(notification);
		
		response.put("Deleted", true);
		
		return response;
		
		
	}
	
	
	@PutMapping( value ="{username}/closeNotification/{notifyId}")
	public NotificationManager closeNotification (@PathVariable ( value ="username") String username 
			, @PathVariable (value ="notifyId") Long notifyId
			) throws Exception{
		
		User user = userServiceRepository.findByUsername(username);
        if( user == null ) {
        	throw new Exception("User Not Found ");
        }
        
        

		NotificationManager  notify =  notifyRepository.findById(notifyId).orElseThrow( 	
				() -> new Exception("Notification Not Found")
				);
		
		
		    notify.setStatus(true);
		

				
		return notifyRepository.save(notify);
		
	}

	
	
	
	
	
	

}

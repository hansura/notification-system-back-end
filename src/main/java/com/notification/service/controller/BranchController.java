package com.notification.service.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.notification.service.model.Branch;
import com.notification.service.service.BranchRepository;
@RestController
@CrossOrigin( origins = { "http://localhost:4200" , "http://localhost" , "http://10.1.80.66"})
@RequestMapping( value ="notification/admin/")
public class BranchController {
	
	@Autowired
	BranchRepository branchRepository;
	
	
	@GetMapping(value ="allBranch/")
	public List<Branch> getAllBranch () throws Exception{
		   
		
		return branchRepository.findAll();
	}
	
	
	@GetMapping( value = "allBranch/{branchId}")
	public ResponseEntity<Branch> getBranchById( @PathVariable (value ="branchId" ) Long branchId) throws Exception{
		
		Branch branch = branchRepository.findById(branchId).orElseThrow(
				
				()-> new Exception( " Branch Id is Not Found !!!!")
				);
				
		
		  return ResponseEntity.ok().body(branch);
		
		
	}
	
	
	@PostMapping( value ="allBranch/addBranch")
	public Branch  addBranch( @RequestBody Branch branch) throws Exception{
		
	     Branch checkBranch = branchRepository.findByBranchCode(branch.getBranchCode());
		  if(checkBranch != null)
			  throw new Exception("Branch Already Exist");
		
		return branchRepository.save(branch);
	}
	
	
	@PutMapping( value ="allBranch/updateBranch/{branchId}")
	public ResponseEntity<Branch> updateBranch( @PathVariable(value ="branchId") Long branchId , @RequestBody Branch branch)throws Exception
	{
		
		Branch localBranch = branchRepository.findById(branchId).orElseThrow(
				() -> new Exception( "Branch Id is Note Found")
				);
		
		 localBranch.setBranchCode(branch.getBranchCode());
		 localBranch.setBranchName(branch.getBranchName());
		 
		 final Branch newBranch = branchRepository.save(localBranch);
		 
		 return ResponseEntity.ok(newBranch);
		
	}
	
	
	@DeleteMapping( value = "allBranch/deleteBranch/{branchId}")
	public Map<String , Boolean> deleteBranch( @PathVariable(value ="branchId") Long branchId) throws Exception{
		
		  Branch branch = branchRepository.findById(branchId).orElseThrow(
				  () -> new Exception("Branch Id Not Found")
				  );
				  
		     branchRepository.delete(branch);

		     Map<String , Boolean> response = new HashMap<String ,Boolean>();
		     
		     response.put("deleted", true);
		     return response;
	}
	
	
	
	


}
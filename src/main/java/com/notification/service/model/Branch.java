package com.notification.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="branch")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" , "notificationManager"})
public class Branch implements Serializable  {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5948080008217513897L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String branchCode;
	
	private String branchName;
	
	@OneToMany(mappedBy = "branch" , fetch = FetchType.LAZY )
	private List<NotificationManager> notificationManager = new ArrayList<NotificationManager>();

	
	
	
	public List<NotificationManager> getNotificationManager() {
		return notificationManager;
	}

	public void setNotificationManager(List<NotificationManager> notificationManager) {
		this.notificationManager = notificationManager;
	}

	public Branch() {
		
	}

	public Branch(Long id, String branchCode, String branchName) {
		
		this.id = id;
		this.branchCode = branchCode;
		this.branchName = branchName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	
	
	
	
}

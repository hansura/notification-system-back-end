package com.notification.service.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="notify")
public class NotificationManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2528334184399756561L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long issueId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name ="branch_id")
	private Branch branch;

	@ManyToOne( fetch =  FetchType.LAZY)
	private Position currentPosition;
	
	@ManyToOne( fetch = FetchType.LAZY)
	private Position previousPosition;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Position removedPosition;
	
	
	private String userId;
	
	private String removedUserId;
	
	private String dateFrom;
	
	private String dateUpto;
	
	
	private String makerId;
	
	
    private boolean status;
	
	
    
    
    
	
	public NotificationManager() {
		
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateUpto() {
		return dateUpto;
	}

	public void setDateUpto(String dateUpto) {
		this.dateUpto = dateUpto;
	}

	public String getMakerId() {
		return makerId;
	}

	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}



	public Position getCurrentPosition() {
		return currentPosition;
	}



	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}



	public Position getPreviousPosition() {
		return previousPosition;
	}



	public void setPreviousPosition(Position previousPosition) {
		this.previousPosition = previousPosition;
	}



	public Position getRemovedPosition() {
		return removedPosition;
	}



	public void setRemovedPosition(Position removedPosition) {
		this.removedPosition = removedPosition;
	}



	public String getRemovedUserId() {
		return removedUserId;
	}



	public void setRemovedUserId(String removedUserId) {
		this.removedUserId = removedUserId;
	}


   
	
	
	
	
	
	
	
	

}

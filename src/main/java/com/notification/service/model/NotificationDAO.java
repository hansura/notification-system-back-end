package com.notification.service.model;

public class NotificationDAO {
	
	private NotificationManager notification;
	
	
	private Position previousPosition;
	
	private Position currentPosition;
	
	private Branch branch;
	
	private Long previousId;
	
	private Long currentId;
	
	private Long branchId;
	
	private Long removedId;
  
	public NotificationManager getNotification() {
		return notification;
	}

	public void setNotification(NotificationManager notification) {
		this.notification = notification;
	}

	public Position getPreviousPosition() {
		return previousPosition;
	}

	public void setPreviousPosition(Position previousPosition) {
		this.previousPosition = previousPosition;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Long getPreviousId() {
		return previousId;
	}

	public void setPreviousId(Long previousId) {
		this.previousId = previousId;
	}

	public Long getCurrentId() {
		return currentId;
	}

	public void setCurrentId(Long currentId) {
		this.currentId = currentId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getRemovedId() {
		return removedId;
	}

	public void setRemovedId(Long removedId) {
		this.removedId = removedId;
	}

	
	
	

}

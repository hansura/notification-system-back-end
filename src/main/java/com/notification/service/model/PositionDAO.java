package com.notification.service.model;

import java.util.ArrayList;
import java.util.List;

public class PositionDAO {

	private Position position;
	
	private List<Role> role =  new ArrayList<Role>();

	private List<Long> roleIds = new ArrayList<>();
	
	 public PositionDAO() {}
	 
	 
	 
	 

	public PositionDAO(Position position, List<Role> role, List<Long> roleIds) {
		this.position = position;
		this.role = role;
		this.roleIds = roleIds;
	}





	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}





	@Override
	public String toString() {
		return "PositionDAO [position=" + position + ", role=" + role + ", roleIds=" + roleIds + ", getPosition()="
				+ getPosition() + ", getRole()=" + getRole() + ", getRoleIds()=" + getRoleIds() + "]";
	}
	




	
	
	
	

	
	
	
}

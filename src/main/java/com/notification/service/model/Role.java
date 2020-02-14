package com.notification.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="role_detail")
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 199253857937377603L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String roleName;
	
	private String roleDescription;
	

	

	
	@ManyToMany( mappedBy ="role" , cascade =  CascadeType.ALL , fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Position> position = new ArrayList<Position>(); 
	
	
	
	
	
	
	
	public Role() {
		
	}

	public Role(Long id, String roleName, String roleDescription) {
		this.id = id;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<Position> getPosition() {
		return position;
	}

	public void setPosition(List<Position> position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleDescription=" + roleDescription + ", position="
				+ position + ", getId()=" + getId() + ", getRoleName()=" + getRoleName() + ", getRoleDescription()="
				+ getRoleDescription() + ", getPosition()=" + getPosition() + "]";
	}

	
	
}

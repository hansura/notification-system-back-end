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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





@Entity
@Table( name ="position")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" , "currentPosition" , "previousPosition" , "removedPosition"})
public class Position  implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5274678423105991168L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String positionName;
	
	private String positionDescription;
	
	@ManyToMany(fetch = FetchType.LAZY )
	@JoinTable(name = "role_detail_position", joinColumns = {
    @JoinColumn(name = "position_id", nullable = false, updatable = false) },
	inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	private List<Role> role   = new  ArrayList<Role>();
	
	
	
	@OneToMany( mappedBy = "currentPosition" )
	private List<NotificationManager> currentPosition = new ArrayList<NotificationManager>();
	
	@OneToMany(mappedBy = "previousPosition" )
	private List<NotificationManager> previousPosition = new ArrayList<NotificationManager>();
	
	@OneToMany(mappedBy = "removedPosition" )
    private List<NotificationManager> removedPosition = new ArrayList<NotificationManager>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionDescription() {
		return positionDescription;
	}

	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}
	
	
	
	
	
	
	

//	@Override
//	public String toString() {
//		return "Position [id=" + id + ", positionName=" + positionName + ", positionDescription=" + positionDescription
//				+ ", role=" + role + ", currentPosition=" + currentPosition + ", previousPosition=" + previousPosition
//				+ ", getId()=" + getId() + ", getPositionName()=" + getPositionName() + ", getPositionDescription()="
//				+ getPositionDescription() + ", getRole()=" + getRole() + "]";
//	}


	

	

	
	
	
	
	
	
}

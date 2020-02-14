package com.notification.service.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notification.service.model.Role;

@Repository
public interface RoleRepositiory extends JpaRepository<Role , Long> {

}

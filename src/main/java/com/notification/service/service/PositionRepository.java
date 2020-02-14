package com.notification.service.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notification.service.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position , Long> {

}

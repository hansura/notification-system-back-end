package com.notification.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.notification.service.model.NotificationManager;

@Repository
public interface NotifyRepository extends CrudRepository<NotificationManager, Long> {

	
	List<NotificationManager> findByBranchId(Long branchId );
	Optional<NotificationManager> findByIdAndBranchId(Long notificationId , Long branchId);
	
	
}

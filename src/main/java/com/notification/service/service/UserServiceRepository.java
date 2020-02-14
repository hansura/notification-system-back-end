package com.notification.service.service;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notification.service.model.User;

@Repository
public interface UserServiceRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}

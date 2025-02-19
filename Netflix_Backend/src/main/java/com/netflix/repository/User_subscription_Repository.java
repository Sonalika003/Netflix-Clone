package com.netflix.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.entity.User_Subscription;

public interface User_subscription_Repository extends JpaRepository<User_Subscription, Integer> {

	List<User_Subscription> findByCreatedAtBefore(LocalDateTime dateTime);
}

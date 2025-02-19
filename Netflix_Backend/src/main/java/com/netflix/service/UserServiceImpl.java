package com.netflix.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.entity.Subscription;
import com.netflix.entity.User;
import com.netflix.entity.User_Subscription;
import com.netflix.model.Response;
import com.netflix.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Response updateUserSubscription(String email, String subscriptionType) throws Exception {
		Optional<User> userOpt = userRepo.findByEmail(email);
		
		if(userOpt.isEmpty()) {
			throw new Exception("User not found with email");
		}
		User user = userOpt.get();
		User_Subscription userSub = user.getSubscription();
		userSub.setSubscription(Subscription.valueOf(subscriptionType.toUpperCase()));
		userSub.setCreatedAt(LocalDateTime.now());
		userSub.setUser(user);
		user.setSubscription(userSub);
		userRepo.save(user);
		return new Response("Subscription successfully Updated");
	}

	@Override
	public List<User> getAllUser() {
		List<User> us = userRepo.findAll();
		return us;
	}

	
}

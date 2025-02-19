package com.netflix.service;

import org.springframework.stereotype.Service;

import com.netflix.entity.RegisterRequest;
import com.netflix.entity.Role;
import com.netflix.entity.Subscription;
import com.netflix.entity.User;
import com.netflix.entity.User_Subscription;
import com.netflix.model.AuthenticationRequest;
import com.netflix.model.AuthenticationResponse;
import com.netflix.model.Response;
import com.netflix.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;
	
	
	public Response register(RegisterRequest request) {
		
		User user = new User();
		
		user.setEmail(request.getEmail());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setPassword(request.getPassword());
		user.setRole(Role.USER);
		
		User_Subscription userSub = new User_Subscription();
		userSub.setSubscription(Subscription.NONE);
		userSub.setUser(user);
		user.setSubscription(userSub);
		
		repository.save(user);
		return new Response("Successfully Register");
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		var user = repository.findByEmail(request.getEmail()).orElseThrow();
		
		if(! user.getPassword().equals(request.getPassword())) {
			throw new Exception("Wrong Credentials");
		}
		return new AuthenticationResponse(user.getFirstName()+" "+user.getLastName(), user.getRole(), user.getSubscription().getSubscription());
	
	}

}

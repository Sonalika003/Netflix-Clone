package com.netflix.service;

import java.util.List;

import com.netflix.entity.User;
import com.netflix.model.Response;

public interface UserService {

	public Response updateUserSubscription(String email,String subscriptionType) throws Exception;
	
	public List<User> getAllUser();
}

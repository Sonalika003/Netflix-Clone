package com.netflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.entity.User;
import com.netflix.model.Response;
import com.netflix.model.SubscriptionRequest;
import com.netflix.service.UserService;

@RestController
@RequestMapping("/auth/v1/user-controller")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PutMapping("/subscription")
	public ResponseEntity<Response> updateUserSubscription(@RequestBody SubscriptionRequest sr) throws Exception{
		Response response = userService.updateUserSubscription(sr.getEmail(), sr.getSubscriptionType());
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.getAllUser();
		return new ResponseEntity<List<User>>(users,HttpStatus.ACCEPTED);
	}
}

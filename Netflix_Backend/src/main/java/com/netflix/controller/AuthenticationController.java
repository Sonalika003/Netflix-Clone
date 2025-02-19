package com.netflix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.entity.RegisterRequest;
import com.netflix.model.AuthenticationRequest;
import com.netflix.model.AuthenticationResponse;
import com.netflix.model.Response;
import com.netflix.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

	private final AuthenticationService service;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(service.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) throws Exception{
		AuthenticationResponse res = service.authenticate(request);
		return new ResponseEntity<AuthenticationResponse>(res,HttpStatus.OK);
	}
}
	
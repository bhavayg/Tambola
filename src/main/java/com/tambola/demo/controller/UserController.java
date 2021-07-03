package com.tambola.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.tambola.demo.model.User;
import com.tambola.demo.model.UserToken;
import com.tambola.demo.response.ServiceResponse;
import com.tambola.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private ServiceResponse<User> response;
	
	@Autowired
	private ServiceResponse<UserToken> loginResponse;
	
	@Autowired
	private UserService service;

	
	@GetMapping("/userHealth")
	public String health(){
		return "Sab changa si";
	}
	
	@PostMapping("/user/add")
	@ResponseBody
	public ServiceResponse<User> add(@RequestBody User user){
		
		System.out.println("In user add");
		System.out.println(user.toString());
		if(user.getUserName()==null||user.getPassword()==null) {
			System.out.println("In user add1");
			response.setMessage("Both fields are required");
			response.setErrorCode(400);
			return response;
		}
		response.setData(service.add(user));
		response.setMessage("Success");
		if (response.getData()==null) {
			response.setMessage("User already exists");
		}
		response.setErrorCode(200);
		return response;
	}
	
	@PostMapping("/user/login")
	@ResponseBody
	public ServiceResponse<UserToken> login(@RequestBody User user){
		System.out.println("In user login");
		System.out.println(user.toString());
		if(user.getUserName()==null||user.getPassword()==null) {
			System.out.println("In user add1");
			loginResponse.setMessage("Both fields are required");
			loginResponse.setErrorCode(400);
			return loginResponse;
		}
		loginResponse.setData(service.generateToken(user));
		
		if (loginResponse.getData()==null) {
			loginResponse.setMessage("User does not exist");
			response.setErrorCode(200);
			return loginResponse;
		}
		System.out.println("In user add2");
		loginResponse.getData().setUserName(user.getUserName());
		loginResponse.setMessage("Success");
		response.setErrorCode(200);
		return loginResponse;
	}
	

}
























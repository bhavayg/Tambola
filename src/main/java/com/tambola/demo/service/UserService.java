package com.tambola.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tambola.demo.dao.UserRespository;
import com.tambola.demo.dao.UserTokenRepository;
import com.tambola.demo.model.User;
import com.tambola.demo.model.UserToken;


@Component
public class UserService {
	
	@Autowired
	private UserRespository repo;
	
	@Autowired
	UserTokenRepository userTokenRepo;
	
	@Autowired
	private RandomService rand;
	
	@Autowired
	private UserToken userToken;
	
	public User add(User user) throws IllegalArgumentException{
		try {
			System.out.println("In user service");
			boolean result =repo.existsById(user.getUserName());
			
			if(result==false) {
				return repo.save(user);
			}else {
				return null;
			}
		
		}catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}
	
	public UserToken generateToken(User user){
		System.out.println("In user generateToken2");
		
		Optional<User> result =repo.findById(user.getUserName());
		
		if(result.isPresent()) {
			
			if(result.get().getPassword().equals(user.getPassword())) {
				
				UserToken dbUserToken = new UserToken();
				dbUserToken=userTokenRepo.findbyUserName(user.getUserName());
				 if(dbUserToken!=null){
					 userTokenRepo.deleteById(dbUserToken.getToken());
				 }
				userToken.setToken(rand.randomString(32));
				userToken.setUserName(user.getUserName());
				System.out.println(userTokenRepo.save(userToken));
				System.out.println(userToken.getToken());
				return userToken;
			}
		}else {
			return null;
		}
		return null;
	}
	
	public boolean userAuthenticity(String token) {
		return userTokenRepo.existsById(token);
	}
	public UserToken findByToken(String token) {	
		Optional<UserToken> userToken = userTokenRepo.findById(token);
		if(userToken.isPresent()) {
			return userToken.get();
		}
		return null;
	}
}


















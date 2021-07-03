package com.tambola.demo.dao;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tambola.demo.model.UserToken;

public interface UserTokenRepository extends MongoRepository<UserToken,String>{
	
	@Query("{'userName':?0}")
	public UserToken findbyUserName(String userName);
	
	
}

package com.tambola.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tambola.demo.model.User;

public interface UserRespository extends MongoRepository<User,String>{

}

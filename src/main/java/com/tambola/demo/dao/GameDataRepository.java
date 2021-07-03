package com.tambola.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.tambola.demo.model.GameData;

public interface GameDataRepository extends MongoRepository<GameData, String>{
	
}

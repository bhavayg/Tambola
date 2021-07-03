package com.tambola.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tambola.demo.model.PlayerData;

public interface PlayerDataRepository extends MongoRepository<PlayerData, String>{
	
	@Query("{'name':?0, 'inviteId':?1}")
	public PlayerData findbyNameAndInviteId(String name, String inviteId);
	
	@Query("{'inviteId'}:?0")
	public List<PlayerData> findAllByInviteId(String inviteId);
}

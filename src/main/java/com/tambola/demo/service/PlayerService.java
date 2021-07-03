package com.tambola.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tambola.demo.dao.PlayerDataRepository;
import com.tambola.demo.model.PlayerData;

@Component
public class PlayerService {
	@Autowired
	PlayerDataRepository playerDatarepo;
	
	@Autowired
	PlayerData playerData;
	
	@Autowired 
	RandomService rand;
	
	
	public PlayerData add(String name, String inviteId) {
		playerData.setName(name);
		playerData.setInviteId(inviteId);
		playerData.setTicket(rand.ticket());
		System.out.println(playerData);
		playerDatarepo.save(playerData);
		return playerData;
	}
	public PlayerData updateTicket(PlayerData playerData, int number) {
		playerDatarepo.delete(playerData);
		rand.updateTicket(playerData.getTicket(),number);
		playerDatarepo.save(playerData);
		return playerData;
	}
	public PlayerData findByNameAndInviteId(String name, String inviteId) {
		return playerDatarepo.findbyNameAndInviteId(name, inviteId);
	}
}

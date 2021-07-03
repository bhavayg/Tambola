package com.tambola.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tambola.demo.dao.GameDataRepository;
import com.tambola.demo.dao.PlayerDataRepository;
import com.tambola.demo.dao.UserTokenRepository;
import com.tambola.demo.dto.PlayerAndTicket;
import com.tambola.demo.model.GameData;
import com.tambola.demo.model.PlayerData;
import com.tambola.demo.model.UserToken;

@Component
public class GameService {
	@Autowired
	GameDataRepository gameDatarepo;

	@Autowired
	UserTokenRepository userTokenrepo;

	@Autowired
	PlayerDataRepository playerDatarepo;

	@Autowired
	UserService userService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	RandomService rand;

	@Autowired
	UserToken userToken;

	@Autowired
	GameData gameData;

	@Autowired
	PlayerData playerData;

	@Autowired
	private PlayerAndTicket playerAndTicket;

	/// Users/weye_tech/mongodb/bin/mongod --dbpath=/Users/weye_tech/mongodb-data
	///
	public GameData add(String token) {

		System.out.println(" in Game add");
		UserToken userToken = new UserToken();
		userToken = userService.findByToken(token);
		if (userToken != null) {
			System.out.println(" in Game add1");
			gameData.setInviteId(rand.randomString(32));
			gameData.setHost(userToken.getUserName());
			gameData.setWinner(null);
			gameDatarepo.save(gameData);
			return gameData;
		}
		System.out.println(" in Game add2");
		return null;

	}

	public boolean gameAuthenticity(String inviteId) {
		Optional<GameData> gameData = gameDatarepo.findById(inviteId);
		if (gameData.isPresent()) {
			if (gameData.get().getWinner() == null) {
				return true;
			}
		}
		return false;
	}

	public boolean hostAuthenticity(String token, String inviteId) {
		
		Optional<GameData> gameData = gameDatarepo.findById(inviteId);
		if (gameData.isPresent()) {
			userToken = userService.findByToken(token);
			System.out.println("host true1");
			if (userToken != null) {
				System.out.println("host true2");
				if (userToken.getUserName().equals(gameData.get().getHost())) {
					System.out.println("host true3");
					return true;
				}
			}
		}
		System.out.println("host false");
		return false;
	}

	public PlayerData join(String token, String inviteId) {
		
		userToken = userService.findByToken(token);
		playerData=playerService.findByNameAndInviteId(userToken.getUserName(), inviteId);
		//System.out.println("in game Service"+playerData);
		if(playerData!=null) {
			System.out.println("in game Service"+playerData.getTicket());
			
			int[][] t=playerData.getTicket();
			for(int i=0;i<5;i++) {
				for(int j=0;j<3;j++) {
					System.out.println(t[i][j]);
				}
			}
			
			return playerData;
		}
		if (userToken != null) {
			if (this.gameAuthenticity(inviteId)) {
				playerData = playerService.add(userToken.getUserName(), inviteId);
				if (playerData != null) {
					return playerData;
				}
			}
		}
		return null;
	}

	public int generateNumber(String token, String inviteId) {
		int randomNumber = (int) ((Math.random() * 49) + 1);
		System.out.println(randomNumber);
		System.out.println(randomNumber);
		if (this.hostAuthenticity(token, inviteId)) {
			List<PlayerData> playerDataList = playerDatarepo.findAllByInviteId(inviteId);
			System.out.println(playerDataList);
			for (PlayerData i : playerDataList) {
				playerService.updateTicket(i, randomNumber);
			}

			return randomNumber;
		}
		return 0;

	}

	public PlayerAndTicket ticket(String token, String inviteId) {
		if (this.gameAuthenticity(inviteId)) {
			System.out.println("Authentic game");
			userToken = userService.findByToken(token);
			if (userToken != null) {
				System.out.println("Authentic token");
				playerData = playerDatarepo.findbyNameAndInviteId(userToken.getUserName(), inviteId);
				if (playerData != null) {
					playerAndTicket.setTicket(playerData.getTicket());
					playerAndTicket.setPlayer(userToken.getUserName());
					return playerAndTicket;
				}
			}
		}
		return null;
	}
}

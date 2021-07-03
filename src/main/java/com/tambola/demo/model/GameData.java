package com.tambola.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection="gameData")
public class GameData {
	
	@Id
	private String inviteId;
	private String host;
	private String winner;
	public String getInviteId() {
		return inviteId;
	}
	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	
	
}

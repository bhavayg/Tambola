package com.tambola.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection="playerData")
public class PlayerData {
	@Id
	private String inviteId;
	private String name;
	private int[][] ticket;
	public String getInviteId() {
		return inviteId;
	}
	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[][] getTicket() {
		return ticket;
	}
	public void setTicket(int[][] ticket) {
		this.ticket = ticket;
	}
	
}

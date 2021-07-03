package com.tambola.demo.dto;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class PlayerAndTicket {
	String player;
	int[][] ticket;
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int[][] getTicket() {
		return ticket;
	}
	public void setTicket(int[][] ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "PlayerAndTicket [player=" + player + ", ticket=" + Arrays.toString(ticket) + "]";
	}
}

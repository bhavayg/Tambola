package com.tambola.demo.response;

import org.springframework.stereotype.Component;

@Component
public class TicketResponse {
	String name;
	int[][] ticket;
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

package com.tambola.demo.response;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberResponse {
	private int randomNumber;

	public int getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
}

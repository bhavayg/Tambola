package com.tambola.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tambola.demo.dto.PlayerAndTicket;
import com.tambola.demo.model.GameData;
import com.tambola.demo.model.PlayerData;
import com.tambola.demo.request.InviteId;
import com.tambola.demo.response.RandomNumberResponse;
import com.tambola.demo.response.ServiceResponse;
import com.tambola.demo.response.TicketResponse;
import com.tambola.demo.service.GameService;


@RestController
public class GameController {
	
	@Autowired
	private ServiceResponse<GameData> gameDataResponse;
	
	@Autowired
	private ServiceResponse<PlayerData>  playerDataResponse;
	
	@Autowired
	private ServiceResponse<RandomNumberResponse>  randomNumberResponse;
	
	@Autowired
	private RandomNumberResponse randomNumber;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private ServiceResponse<TicketResponse> ticketResponse;
	
	@Autowired
	private TicketResponse ticket;
	
	@Autowired
	private GameData gameData;

	@Autowired
	private PlayerData playerData;
	
	@Autowired
	private PlayerAndTicket playerAndTicket;
	
	@PostMapping("/game/create")
	public ServiceResponse<GameData> create(@RequestHeader("token") String token) {
		
		System.out.println(" in Game create");
		
		gameData = gameService.add(token);
		if(gameData!=null) {
			System.out.println(" in Game create2");
			gameDataResponse.setData(gameData);
			gameDataResponse.setErrorCode(200);
			gameDataResponse.setMessage("Game Created Success");
			return gameDataResponse;
		}
		
		gameDataResponse.setErrorCode(401);
		gameDataResponse.setMessage("User Authentication fail");
		return gameDataResponse;
	
	}
	@PostMapping("/game/join")
	public ServiceResponse<PlayerData> join(@RequestHeader("token") String token, @RequestBody InviteId inviteId){
		playerData=gameService.join(token, inviteId.getInviteId());
		if(playerData!=null) {
			playerDataResponse.setData(playerData);
			playerDataResponse.setErrorCode(200);
			playerDataResponse.setMessage("Game Created Success");
			return playerDataResponse;
		}
		playerDataResponse.setData(null);
		playerDataResponse.setErrorCode(401);
		playerDataResponse.setMessage("joining Failed");
		return playerDataResponse;
	}
	@PostMapping("/game/randomNumber")
	public ServiceResponse<RandomNumberResponse> randomNumber(@RequestHeader("token") String token, @RequestBody InviteId inviteId){
		randomNumber.setRandomNumber(gameService.generateNumber(token, inviteId.getInviteId()));
		if(randomNumber.getRandomNumber()==0) {
			randomNumberResponse.setData(null);
			randomNumberResponse.setErrorCode(200);
			randomNumberResponse.setMessage("Some Error Occured");
			return randomNumberResponse;
		}
		randomNumberResponse.setErrorCode(200);
		randomNumberResponse.setMessage("Success");
		randomNumberResponse.setData(randomNumber);
		return randomNumberResponse;
	}
	@GetMapping("/game/ticket")
	public ServiceResponse<TicketResponse> getTicket(@RequestHeader("token") String token, @RequestBody InviteId inviteId) {
		playerAndTicket = gameService.ticket(token, inviteId.getInviteId());
		if(playerAndTicket!=null) {
			System.out.println(playerAndTicket.toString());
			System.out.println(playerAndTicket.getTicket());
			ticket.setName(playerAndTicket.getPlayer());
			ticket.setTicket(playerAndTicket.getTicket());
			ticketResponse.setData(ticket);
			ticketResponse.setErrorCode(200);
			ticketResponse.setMessage("Success");
			return ticketResponse;
		}
		ticketResponse.setErrorCode(200);
		ticketResponse.setMessage("ticket Failure");
		return ticketResponse;
	}
}

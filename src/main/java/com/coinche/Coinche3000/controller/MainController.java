package com.coinche.Coinche3000.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coinche.Coinche3000.object.Player;
import com.coinche.Coinche3000.service.GameService;

@RestController
public class MainController {

	@Autowired
	GameService gameService;
	
	@PostMapping("/create-new-game/{scoringLimit}")
	public int createNewGame(@PathVariable Integer scoringLimit, @RequestBody Player p1 ) {
		
		int id =  gameService.createGame(p1, scoringLimit);
		gameService.displayGame();
		return id;
	}
}

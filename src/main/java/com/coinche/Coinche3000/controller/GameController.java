package com.coinche.Coinche3000.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.object.Player;
import com.coinche.Coinche3000.service.GameService;

@RestController()
@RequestMapping("game")
public class GameController {

	@Autowired
	GameService gameService;
	
	@PostMapping("/create-new/{scoringLimit}")
	public Game createNewGame(@PathVariable Integer scoringLimit, @RequestBody Player p1 ) {
		
		return gameService.createGame(p1, scoringLimit);
	}
	
	@PostMapping("/join/{gameId}")
	public Game joinGame(@PathVariable Integer gameId, @RequestBody Player player ) {
		
		try {
			return gameService.joinGame(gameId, player);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/")
	public List<Game> getAll() {
			
		return gameService.getAll();
	}
}

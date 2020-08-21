package com.coinche.Coinche3000.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.coinche.Coinche3000.exception.NotFoundException;
import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.object.Player;
import com.coinche.Coinche3000.repository.GameRepository;
import com.coinche.Coinche3000.service.GameService;
import com.coinche.Coinche3000.service.RoundService;

@RestController()
@RequestMapping("game")
public class GameController {

	@Autowired
	GameService gameService;
	
	@Autowired
	RoundService roundService;
	@Autowired
	GameRepository gameRepository;
	
	@CrossOrigin
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
			System.out.println(e.getMessage());
			ResponseStatusException ex = new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Game not found", e);
			throw ex;
//			throw new ResponseStatusException(
//	           HttpStatus.NOT_FOUND, "Game not found", e);
		}
	}
	@GetMapping("/start/{gameId}")
	public Game startGame(@PathVariable Integer gameId) {
		
		try {
			return gameService.startGame(gameId);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/")
	public List<Game> getAll() {
			
		return gameService.getAll();
	}
	
	@PostMapping("/check-changes/{gameId}")
	public Game checkChanges(@PathVariable Integer gameId, @RequestBody String waitingPlayerPseudo ) {
		
		try {
			return gameService.checkChanges(gameId, waitingPlayerPseudo);		
		} catch (NotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			ResponseStatusException ex = new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Game not found", e);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/restart-round/{gameId}")
	public Game restartRound(@PathVariable Integer gameId ) {
		Game game;
		try {
			game = gameService.getGameById(gameId);
			roundService.restartRound(game);
			gameRepository.save(game);
			return game;
		} catch (NotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			ResponseStatusException ex = new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Game not found", e);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@CrossOrigin
	@GetMapping("/test")
	public String test() {
			
		return "Welcome to the game controller";
	}
}

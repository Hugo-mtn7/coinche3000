package com.coinche.Coinche3000.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinche.Coinche3000.Constants;
import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.object.Player;

@RestController
public class MainController {

	@RequestMapping("/create-new-game")
	public String createNewGame() {
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Soso",Constants.TEAM1));
		players.add(new Player("Pierre",Constants.TEAM2));
		players.add(new Player("Scott",Constants.TEAM1));
		players.add(new Player("Quinty",Constants.TEAM2));
		
		Game game = new Game(players,1500);
		game.displayPlayers();
		return "nouveau jeu créé";
	}
}

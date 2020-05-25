package com.coinche.Coinche3000.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinche.Coinche3000.Constants;
import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.object.Player;

@Service
public class GameService {

	@Autowired
	Game game;
	
	public int createGame(Player p1, int scoringLimit) {
		Random rand = new Random();
		
		game.setId(rand.nextInt(9999999));
		game.setPlayers(new ArrayList<Player>(
			      Arrays.asList(p1)));
		game.setScoringLimit(scoringLimit);
		game.setCurrentDealerPosition(0);
		
		return game.getId();
	}

	public void displayGame() {
		System.out.println("Game id : " + game.getId());
		System.out.println("Scoring limit : " + game.getScoringLimit());
		displayPlayers();
	}
	public void displayPlayers() {
		System.out.println("TEAM 1 :");
		for(Player player : game.getPlayers()) {
			if(player.getTeam() == Constants.TEAM1) {
				player.displayPlayer();
			}
		}
		System.out.println("TEAM 2 :");
		for(Player player : game.getPlayers()) {
			if(player.getTeam() == (Constants.TEAM2)) {
				player.displayPlayer();
			}
		}
	}
}

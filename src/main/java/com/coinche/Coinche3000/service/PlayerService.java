package com.coinche.Coinche3000.service;

import org.springframework.stereotype.Service;

import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.object.Player;

@Service
public class PlayerService {

	public Game emptyPlayersHands(Game game) {
		for(Player player : game.getPlayers()){
			player.setHand(null);
		}
		return game;
	}
}

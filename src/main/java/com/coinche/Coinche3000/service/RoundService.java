package com.coinche.Coinche3000.service;

import org.springframework.stereotype.Service;

import com.coinche.Coinche3000.object.CardDeck;
import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.object.Round;

@Service
public class RoundService {

	
	public Round startRound(Game game) {
		Round round = new Round();
		round.setRoundNumber(game.getCurrentRoundNumber());
		
		CardDeck cardDeck = new CardDeck();
		cardDeck.shuffle();
		cardDeck.distribute(game.getCurrentDealerPosition(), game.getPlayers());
		
		return round;
		
		
	}
}

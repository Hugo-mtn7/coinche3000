package com.coinche.Coinche3000.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinche.Coinche3000.object.CardDeck;
import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.object.Round;

@Service
public class RoundService {

	@Autowired
	GameService gameService;
	@Autowired
	PlayerService playerService;
	
	public Round startRound(Game game) {
		Round round = new Round();
		round.setRoundNumber(game.getCurrentRoundNumber());
		
		CardDeck cardDeck = new CardDeck();
		cardDeck.shuffle();
		cardDeck.distribute(game.getCurrentDealerPosition(), game.getPlayers());
		
		game.setIsBetTurnOver(false);
		return round;	
	}
	
	public void restartRound(Game game) {
		
		for(Round round : game.getRounds()){
			if(round.getRoundNumber() == game.getCurrentRoundNumber())
				round.setBet(null);
		}
		
		game.setCurrentDealerPosition(CommonService.getNextPosition(game.getCurrentDealerPosition()));
		game.setCurrentPlayer(gameService.getPlayerPseudoFromPosition(
				game, CommonService.getNextPosition(game.getCurrentDealerPosition())));
		
		//game = playerService.emptyPlayersHands(game);
		CardDeck cardDeck = new CardDeck();
		cardDeck.shuffle();
		cardDeck.distribute(game.getCurrentDealerPosition(), game.getPlayers());
		
		game.setPassCounter(0);
		game.setIsBetTurnOver(false);
	}
	

}

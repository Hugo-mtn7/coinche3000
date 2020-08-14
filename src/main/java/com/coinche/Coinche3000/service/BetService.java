package com.coinche.Coinche3000.service;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinche.Coinche3000.Constants;
import com.coinche.Coinche3000.exception.NotFoundException;
import com.coinche.Coinche3000.object.Bet;
import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.object.Round;
import com.coinche.Coinche3000.repository.GameRepository;

@Service
public class BetService {

	@Autowired
	RoundService roundService;
	@Autowired
	GameService gameService;
	
	@Autowired
	GameRepository gameRepository;
	
	public Game placeBet(int gameId, Bet bet) throws NotFoundException, Exception {

		Game game = gameService.getGameById(gameId);
		if(bet.getPseudo().equals(game.getCurrentPlayer())) {
			if(isBetValid(game, bet)) {
				if(bet.getValue() == Constants.PASS) {
					//TODO manage end of bet
					game.setPassCounter(game.getPassCounter() + 1);
				}
				else
				{
					for(Round round : game.getRounds()){
						if(round.getRoundNumber() == game.getCurrentRoundNumber())
							round.setBet(bet);
					}
					game.setPassCounter(0);
				}
				game.setCurrentPlayer(gameService.getNextPlayerPseudo(game));
				gameService.saveGame(game);
				return game;
			}
			else{
				throw new Exception("Bet isn't above last player bet");
			}		
		}
		else {
			throw new Exception("Not your turn to bet !");
		}
			
	}
	
	public Boolean isBetValid(Game game, Bet bet) {
		Bet currentBet = getCurrentBet(game);
		
		if((null == currentBet && game.getPassCounter() < 4)
			|| ((bet.getValue() > currentBet.getValue() || bet.getValue() == Constants.PASS)
			&& IntStream.of(Constants.VALID_BET_VALUE).anyMatch(x -> x == bet.getValue())
			&& game.getPassCounter() < 3))
			return true;
		else
			return false;
		
	}
	
	public Bet getCurrentBet(Game game) {
		for(Round round : game.getRounds()){
			if(round.getRoundNumber() == game.getCurrentRoundNumber())
				return round.getBet();
		}
		return null;
	}
}

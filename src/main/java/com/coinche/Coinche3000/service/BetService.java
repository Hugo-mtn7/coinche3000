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
		if(game.getIsBetTurnOver() == false) {

			if(bet.getPseudo().equals(game.getCurrentPlayer())) {
				Bet currentBet = getCurrentBet(game);
				if(isBetValid(game, bet, currentBet)) {
					if(bet.getValue() == Constants.PASS) {
						if(isLastBet(game, currentBet)) {
							//End bet turn
							game.setIsBetTurnOver(true);
							game.setPassCounter(0);
							game.setCurrentPlayer(gameService.getPlayerPseudoFromPosition(
									game, CommonService.getNextPosition(game.getCurrentDealerPosition())));
							gameService.saveGame(game);
							return game;
						}
						else
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
		else {
			throw new Exception("Bets are over !");
		}
			
	}
	
	public Boolean isBetValid(Game game, Bet bet, Bet currentBet) {
		
		if((null == currentBet && game.getPassCounter() < 4)
			|| ((bet.getValue() > currentBet.getValue() || bet.getValue() == Constants.PASS)
			&& IntStream.of(Constants.VALID_BET_VALUE).anyMatch(x -> x == bet.getValue())
			&& game.getPassCounter() < 3))
			return true;
		else
			return false;
		
	}
	
	public Boolean isLastBet(Game game, Bet currentBet) {
		
		if((null == currentBet && game.getPassCounter() >= 3)
			|| (null != currentBet && game.getPassCounter() >= 2))
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

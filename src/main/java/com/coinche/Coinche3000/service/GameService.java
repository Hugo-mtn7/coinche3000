package com.coinche.Coinche3000.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinche.Coinche3000.Constants;
import com.coinche.Coinche3000.exception.NotFoundException;
import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.object.Player;
import com.coinche.Coinche3000.object.Round;
import com.coinche.Coinche3000.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	RoundService roundService;
	
	@Autowired
	GameRepository gameRepository;
	
	public Game createGame(Player p1, int scoringLimit) {
		Random rand = new Random();
		
		Game newGame = new Game();
		newGame.setId(Integer.toString(rand.nextInt(9999999)));
		newGame.setPlayers(new ArrayList<Player>(
			      Arrays.asList(p1)));
		newGame.setScoringLimit(scoringLimit);
		newGame.setCurrentDealerPosition(1);
		newGame.setCurrentRoundNumber(0);
		newGame.setPassCounter(0);
		
		gameRepository.save(newGame);
		
		return newGame;
	}
	
	public Game joinGame(Integer gameId, Player player) throws NotFoundException {
		Optional<Game> optionalGameToJoin = gameRepository.findById(Integer.toString(gameId));
		if(optionalGameToJoin.isPresent()) {
			Game gameToJoin = optionalGameToJoin.get();
			if(checkPlayerValidity(gameToJoin,player)) {
				gameToJoin.getPlayers().add(player);
				gameRepository.save(gameToJoin);			
			}
			return gameToJoin;
		}
		else
			throw new NotFoundException("id not found");
		
	}
	
	public Game startGame(Integer gameId) throws NotFoundException, Exception {
		Optional<Game> optionalGameToJoin = gameRepository.findById(Integer.toString(gameId));
		if(optionalGameToJoin.isPresent()) {
			Game gameToStart = optionalGameToJoin.get();
			if(isGameReady(gameToStart)) {
				gameToStart.setCurrentRoundNumber(1);
				setPlayersPositions(gameToStart);
				gameToStart.setRounds(new ArrayList<Round>(
						Arrays.asList(roundService.startRound(gameToStart)))); 
				gameToStart.setCurrentPlayer(getPlayerPseudoFromPosition(gameToStart,2));
				gameRepository.save(gameToStart);
				return gameToStart;
			}
			else
				throw new Exception("Game is not ready");
		}
		else
			throw new NotFoundException("id not found");
	}
	
	public Game getGameById(Integer gameId) throws NotFoundException, Exception {
		Optional<Game> optionalGame = gameRepository.findById(Integer.toString(gameId));
		if(optionalGame.isPresent())
			return optionalGame.get();
		else
			throw new NotFoundException("id not found");
	}
	
	public Boolean saveGame(Game game) {
		gameRepository.save(game);
		return true;
		
	}
	
	public Boolean isGameReady(Game game){
		int nbTeam1 = 0;
		int nbTeam2 = 0;
		for(Player player : game.getPlayers()) {
			if(player.getTeam() == Constants.TEAM1)
				nbTeam1++;
			else if(player.getTeam() == Constants.TEAM2)
				nbTeam2++;
		}
		if(nbTeam1 != 2 && nbTeam2 !=2)
			return false;
		if(game.getScoringLimit() <= 0)
			return false;
		if(game.getCurrentRoundNumber() != 0)
			return false;
		return true;
		
	}
	//TODO finish and manage exception message well... check if 2 players have the same pseudo
	public Boolean checkPlayerValidity(Game game, Player playerToAdd) {
		
		if(null != playerToAdd && 0!=playerToAdd.getTeam() && game.getPlayers().size()<4) {
			int nbTeam1 = 0;
			int nbTeam2 = 0;
			for(Player player : game.getPlayers()) {
				if(player.getTeam() == Constants.TEAM1)
					nbTeam1++;
				else if(player.getTeam() == Constants.TEAM2)
					nbTeam2++;
			}
			if(playerToAdd.getTeam() == Constants.TEAM1 && nbTeam1 <2)
				return true;
			else if(playerToAdd.getTeam() == Constants.TEAM2 && nbTeam2 <2)
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public void setPlayersPositions(Game game) {
		game.getPlayers().get(0).setPosition(1);
		
		if(game.getPlayers().get(1).getTeam() == game.getPlayers().get(0).getTeam())
			game.getPlayers().get(1).setPosition(3);
		else
			game.getPlayers().get(1).setPosition(2);
		
		if(game.getPlayers().get(1).getPosition() == 3) {
			game.getPlayers().get(2).setPosition(2);
			game.getPlayers().get(3).setPosition(4);
		}
		else if(game.getPlayers().get(2).getTeam() == game.getPlayers().get(0).getTeam()){
			game.getPlayers().get(2).setPosition(3);
			game.getPlayers().get(3).setPosition(4);
		}
		else {
			game.getPlayers().get(2).setPosition(4);
			game.getPlayers().get(3).setPosition(3);
		}
	}
	
	public List<Game> getAll() {
		
		return gameRepository.findAll();	
	}
	
	public String getPlayerPseudoFromPosition(Game game, Integer position) {
		for(Player player : game.getPlayers()){
			if(player.getPosition() == position)
				return player.getPseudo();
		}
		return null;
	}
	
	public String getNextPlayerPseudo(Game game) {
		Integer currentPosition = null;
		for(Player player : game.getPlayers()){
			if(player.getPseudo().equals(game.getCurrentPlayer()))
				currentPosition = player.getPosition();
		}
		
		Integer nextPosition = 1;
		if(null != currentPosition)
			nextPosition = CommonService.getNextPosition(currentPosition);
		
		for(Player player : game.getPlayers()){
			if(player.getPosition() == nextPosition)
				return player.getPseudo();
		}
		
		return null;
	}

//	public void displayGame() {
//		System.out.println("Game id : " + game.getId());
//		System.out.println("Scoring limit : " + game.getScoringLimit());
//		displayPlayers();
//	}
//	public void displayPlayers() {
//		System.out.println("TEAM 1 :");
//		for(Player player : game.getPlayers()) {
//			if(player.getTeam() == Constants.TEAM1) {
//				player.displayPlayer();
//			}
//		}
//		System.out.println("TEAM 2 :");
//		for(Player player : game.getPlayers()) {
//			if(player.getTeam() == (Constants.TEAM2)) {
//				player.displayPlayer();
//			}
//		}
//	}
}

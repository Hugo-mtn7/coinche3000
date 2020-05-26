package com.coinche.Coinche3000.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.coinche.Coinche3000.Constants;
import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.object.Player;
import com.coinche.Coinche3000.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	GameRepository gameRepository;
	
	public Game createGame(Player p1, int scoringLimit) {
		Random rand = new Random();
		
		Game newGame = new Game();
		newGame.setId(Integer.toString(rand.nextInt(9999999)));
		newGame.setPlayers(new ArrayList<Player>(
			      Arrays.asList(p1)));
		newGame.setScoringLimit(scoringLimit);
		newGame.setCurrentDealerPosition(0);
		
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
			throw new NotFoundException();
		
	}
	
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

	public List<Game> getAll() {
		
		return gameRepository.findAll();	
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

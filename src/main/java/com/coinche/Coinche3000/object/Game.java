package com.coinche.Coinche3000.object;

import java.util.ArrayList;
import java.util.Random;

import com.coinche.Coinche3000.Constants;

public class Game {
	private int id;
	private ArrayList<Player> players;
	private CardDeck cardDeck;
	private int scoringLimit;
	private int currentDealerPosition;
	
	public Game(ArrayList<Player> players, int scoringLimit) {
		Random rand = new Random();
		
		setId(rand.nextInt(9999999));	
		setCardDeck(new CardDeck());
		setPlayers(players);
		setScoringLimit(scoringLimit);
		setCurrentDealerPosition(0);
	}

	public void displayPlayers() {
		System.out.println("TEAM 1 :");
		for(Player player : players) {
			if(player.getTeam() == Constants.TEAM1) {
				player.displayPlayer();
			}
		}
		System.out.println("TEAM 2 :");
		for(Player player : players) {
			if(player.getTeam() == (Constants.TEAM2)) {
				player.displayPlayer();
			}
		}
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public CardDeck getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}

	public int getScoringLimit() {
		return scoringLimit;
	}

	public void setScoringLimit(int scoringLimit) {
		this.scoringLimit = scoringLimit;
	}

	public int getCurrentDealerPosition() {
		return currentDealerPosition;
	}

	public void setCurrentDealerPosition(int currentDealerPosition) {
		this.currentDealerPosition = currentDealerPosition;
	}
}

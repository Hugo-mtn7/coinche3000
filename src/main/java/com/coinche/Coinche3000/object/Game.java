package com.coinche.Coinche3000.object;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Game {
	@Id
	private String id;
	private ArrayList<Player> players;
	private ArrayList<Round> rounds;
	private int scoringLimit;
	private int currentDealerPosition;
	private int currentRoundNumber;
	private String currentPlayer;
	private int passCounter;
	private Boolean isBetTurnOver;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
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

	public ArrayList<Round> getRounds() {
		return rounds;
	}

	public void setRounds(ArrayList<Round> rounds) {
		this.rounds = rounds;
	}

	public int getCurrentRoundNumber() {
		return currentRoundNumber;
	}

	public void setCurrentRoundNumber(int currentRoundNumber) {
		this.currentRoundNumber = currentRoundNumber;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getPassCounter() {
		return passCounter;
	}

	public void setPassCounter(int passCounter) {
		this.passCounter = passCounter;
	}

	public Boolean getIsBetTurnOver() {
		return isBetTurnOver;
	}

	public void setIsBetTurnOver(Boolean isBetTurnOver) {
		this.isBetTurnOver = isBetTurnOver;
	}



}

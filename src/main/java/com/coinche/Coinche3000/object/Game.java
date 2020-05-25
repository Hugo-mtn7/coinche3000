package com.coinche.Coinche3000.object;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Game {
	private int id;
	private ArrayList<Player> players;
	private int scoringLimit;
	private int currentDealerPosition;
	
	
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

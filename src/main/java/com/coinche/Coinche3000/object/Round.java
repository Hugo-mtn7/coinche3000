package com.coinche.Coinche3000.object;

import java.util.ArrayList;

public class Round {
	private Integer roundNumber;
	private Bet bet;
	private String trump;
	private ArrayList<Card> team1CardsWon;
	private ArrayList<Card> team2CardsWon;
	
	public Integer getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(Integer roundNumber) {
		this.roundNumber = roundNumber;
	}
	public Bet getBet() {
		return bet;
	}
	public void setBet(Bet bet) {
		this.bet = bet;
	}
	public String getTrump() {
		return trump;
	}
	public void setTrump(String trump) {
		this.trump = trump;
	}
	public ArrayList<Card> getTeam1CardsWon() {
		return team1CardsWon;
	}
	public void setTeam1CardsWon(ArrayList<Card> team1CardsWon) {
		this.team1CardsWon = team1CardsWon;
	}
	public ArrayList<Card> getTeam2CardsWon() {
		return team2CardsWon;
	}
	public void setTeam2CardsWon(ArrayList<Card> team2CardsWon) {
		this.team2CardsWon = team2CardsWon;
	}

}

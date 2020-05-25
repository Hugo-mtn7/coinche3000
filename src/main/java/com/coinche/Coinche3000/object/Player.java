package com.coinche.Coinche3000.object;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	private int code;
	private String pseudo;
	private int team;
	private ArrayList<Card> hand;
	private ArrayList<Card> cardsWon;
	
	public Player(String pseudo, int team) {
		Random rand = new Random();
		
		setCode(rand.nextInt(9999999));
		setPseudo(pseudo);
		setTeam(team);
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public ArrayList<Card> getHand() {
		return hand;
	}
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	public ArrayList<Card> getCardsWon() {
		return cardsWon;
	}
	public void setCardsWon(ArrayList<Card> cardsWon) {
		this.cardsWon = cardsWon;
	}
	
}

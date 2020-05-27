package com.coinche.Coinche3000.object;

import java.util.ArrayList;

public class Player {
	private String pseudo;
	private int team;
	private int position;
	private ArrayList<Card> hand;
	
	public Player(String pseudo, int team) {
		setPseudo(pseudo);
		setTeam(team);
	}
	
	public void displayPlayer() {
		System.out.println(this.getPseudo() + ", team " + this.getTeam());
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	
}

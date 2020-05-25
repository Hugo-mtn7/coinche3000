package com.coinche.Coinche3000.object;

public class Player {
	private String pseudo;
	private int team;
	
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

	
}

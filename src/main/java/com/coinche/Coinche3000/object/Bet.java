package com.coinche.Coinche3000.object;

import org.springframework.stereotype.Component;

@Component
public class Bet {
	
	private String pseudo;
	private int value;
	private String color;

	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}

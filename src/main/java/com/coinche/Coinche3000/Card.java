package com.coinche.Coinche3000;

import org.springframework.stereotype.Component;

@Component
public class Card {

	String value;
	String color;
	Integer point;
	Integer trumpPoint;
	
	public void displayCard() {
		System.out.println("("+this.getValue()+ ":" + this.getColor()+") value : " + this.getPoint() +" | trump value : " +this.getTrumpPoint());
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getTrumpPoint() {
		return trumpPoint;
	}
	public void setTrumpPoint(Integer trumpPoint) {
		this.trumpPoint = trumpPoint;
	}
	
	
}

package com.coinche.Coinche3000.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.stereotype.Component;

import com.coinche.Coinche3000.Constants;

@Component
public class CardDeck {
	
	public static final ArrayList<String> cardValueRange= new ArrayList<String>(
		      Arrays.asList("7","8","9","10",Constants.JACK,Constants.QUEEN, Constants.KING, Constants.ACE));
	public static final ArrayList<String> cardColorRange= new ArrayList<String>(
		      Arrays.asList(Constants.CLUB,Constants.SPADE, Constants.HEART, Constants.DIAMOND));

	public CardDeck() {
		set32CardDeck();
	}
	
	private ArrayList<Card> cardDeck;
	
	public ArrayList<Card> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(ArrayList<Card> cardDeck) {
		this.cardDeck = cardDeck;
	}

	public void set32CardDeck(){
		ArrayList<Card> cardDeck = new ArrayList<Card>();
		for(String cardColor : cardColorRange) {
			for(String cardValue : cardValueRange) {
				Card card = new Card();
				card.setColor(cardColor);
				card.setValue(cardValue);
				assignPoints(card);
				cardDeck.add(card);
			}
		}
		setCardDeck(cardDeck);
	}
	
	public void displayCardDeck() {
		System.out.println("Number of cards : " + this.cardDeck.size());
		for(Card card : this.cardDeck) {		
			card.displayCard();
		}
	}
	
	public void shuffle() {
		Collections.shuffle(this.cardDeck);
	}
	private void assignPoints(Card card) {
		
		switch(card.getValue()) {
		case "9":
			card.setPoint(0);
			card.setTrumpPoint(14);
			break;
		case "10":
			card.setPoint(10);
			card.setTrumpPoint(10);
			break;
		case Constants.JACK:
			card.setPoint(2);
			card.setTrumpPoint(20);
			break;
		case Constants.QUEEN:
			card.setPoint(3);
			card.setTrumpPoint(3);
			break;
		case Constants.KING:
			card.setPoint(4);
			card.setTrumpPoint(4);
			break;
		case Constants.ACE:
			card.setPoint(11);
			card.setTrumpPoint(11);
			break;
		default:
			card.setPoint(0);
			card.setTrumpPoint(0);
			
		}
	}
}

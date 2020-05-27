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
	
	public void distribute(int currentDealerPosition, ArrayList<Player> players) {
		ArrayList<Card> hand = new ArrayList<Card>();
		
		for(Player player : players) {
			hand.clear();
			switch(player.getPosition() - currentDealerPosition) {
				case 1: case -3:
					player.setHand(new ArrayList<Card>(Arrays.asList(
							this.getCardDeck().get(0),
							this.getCardDeck().get(1),
							this.getCardDeck().get(2),
							this.getCardDeck().get(12),
							this.getCardDeck().get(13),
							this.getCardDeck().get(20),
							this.getCardDeck().get(21),
							this.getCardDeck().get(22)
							)));
					break;
				case 2: case -2:
					player.setHand(new ArrayList<Card>(Arrays.asList(
							this.getCardDeck().get(3),
							this.getCardDeck().get(4),
							this.getCardDeck().get(5),
							this.getCardDeck().get(14),
							this.getCardDeck().get(15),
							this.getCardDeck().get(23),
							this.getCardDeck().get(24),
							this.getCardDeck().get(25)
							)));
					break;
				case 3: case -1:
					player.setHand(new ArrayList<Card>(Arrays.asList(
							this.getCardDeck().get(6),
							this.getCardDeck().get(7),
							this.getCardDeck().get(8),
							this.getCardDeck().get(16),
							this.getCardDeck().get(17),
							this.getCardDeck().get(26),
							this.getCardDeck().get(27),
							this.getCardDeck().get(28)
							)));
					break;
				case 0: 
					player.setHand(new ArrayList<Card>(Arrays.asList(
							this.getCardDeck().get(9),
							this.getCardDeck().get(10),
							this.getCardDeck().get(11),
							this.getCardDeck().get(18),
							this.getCardDeck().get(19),
							this.getCardDeck().get(29),
							this.getCardDeck().get(30),
							this.getCardDeck().get(31)
							)));
					break;
			}
		}
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

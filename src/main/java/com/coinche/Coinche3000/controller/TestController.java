package com.coinche.Coinche3000.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinche.Coinche3000.object.CardDeck;


@RestController
public class TestController {
	
	
	@RequestMapping("/")
	public String index() {
		CardDeck cardDeck = new CardDeck();
		cardDeck.displayCardDeck();
		System.out.println("********mélange***********");
		cardDeck.shuffle();
		cardDeck.displayCardDeck();
		return "voila";
	}
}

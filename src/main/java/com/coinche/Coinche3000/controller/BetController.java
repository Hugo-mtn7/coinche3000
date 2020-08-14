package com.coinche.Coinche3000.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinche.Coinche3000.exception.NotFoundException;
import com.coinche.Coinche3000.object.Bet;
import com.coinche.Coinche3000.object.Game;
import com.coinche.Coinche3000.service.BetService;

@RestController()
@RequestMapping("bet")
public class BetController {

	@Autowired
	BetService betService;
	
	@PostMapping("/place/{gameId}")
	public Game placeBet(@PathVariable Integer gameId, @RequestBody Bet bet) {
		
		try {
			return betService.placeBet(gameId, bet);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}

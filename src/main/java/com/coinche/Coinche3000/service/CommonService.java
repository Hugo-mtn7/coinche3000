package com.coinche.Coinche3000.service;


public class CommonService {

	public static int getNextPosition(int currentPosition) {
		if (currentPosition < 4 )
			return currentPosition+1;
		else
			return 1;
	}
}

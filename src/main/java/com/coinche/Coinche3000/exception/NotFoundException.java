package com.coinche.Coinche3000.exception;

public class NotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

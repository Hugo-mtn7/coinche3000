package com.coinche.Coinche3000.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Game not found")
public class NotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

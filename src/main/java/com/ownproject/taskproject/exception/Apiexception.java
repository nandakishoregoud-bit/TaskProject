package com.ownproject.taskproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class Apiexception extends RuntimeException {

	private String message;
	
	public Apiexception(String message) {
		super(message);
		this.message = message;
	}
}

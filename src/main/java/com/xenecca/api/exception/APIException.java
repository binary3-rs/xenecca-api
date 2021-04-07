package com.xenecca.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class APIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public APIException(String message) {
		super(message);
	}

}
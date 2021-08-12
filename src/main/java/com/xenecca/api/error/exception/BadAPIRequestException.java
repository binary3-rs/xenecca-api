package com.xenecca.api.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadAPIRequestException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 487518728380001503L;

	public BadAPIRequestException(String message) {
		super(message);
	}

}

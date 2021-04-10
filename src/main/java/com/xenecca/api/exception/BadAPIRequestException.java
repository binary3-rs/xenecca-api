package com.xenecca.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadAPIRequestException extends APIException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 487518728380001503L;

	public BadAPIRequestException(String message) {
		super(message);
	}

}

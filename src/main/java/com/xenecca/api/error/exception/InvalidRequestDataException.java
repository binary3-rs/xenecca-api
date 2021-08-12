package com.xenecca.api.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestDataException extends ApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9130932398405039047L;

	public InvalidRequestDataException(String message) {
		super(message);
	}

}
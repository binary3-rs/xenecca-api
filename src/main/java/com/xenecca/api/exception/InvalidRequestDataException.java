package com.xenecca.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestDataException extends APIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9130932398405039047L;

	public InvalidRequestDataException(String message) {
		super(message);
	}

}
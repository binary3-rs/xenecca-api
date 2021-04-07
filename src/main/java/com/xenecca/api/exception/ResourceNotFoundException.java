package com.xenecca.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends APIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1586748792070288999L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
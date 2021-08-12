package com.xenecca.api.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1586748792070288999L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
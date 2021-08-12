package com.xenecca.api.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileStorageException extends ApiException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6487526539841242326L;

	public FileStorageException(String message) {
		super(message);
	}

}
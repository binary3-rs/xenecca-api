package com.xenecca.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileStorageException extends APIException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6487526539841242326L;

	public FileStorageException(String message) {
		super(message);
	}

}
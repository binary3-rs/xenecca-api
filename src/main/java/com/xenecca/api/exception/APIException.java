package com.xenecca.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class APIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public APIException(String message) {
		super(message);
	}

}
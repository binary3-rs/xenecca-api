package com.xenecca.api.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SubscriptionAlreadyExistsException extends ApiException {
	private static final long serialVersionUID = -4130601503929686964L;

	public SubscriptionAlreadyExistsException(String message) {
		super(message);
	}

}

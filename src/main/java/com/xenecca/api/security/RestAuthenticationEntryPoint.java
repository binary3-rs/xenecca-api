package com.xenecca.api.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.xenecca.api.exception.APIExceptionHandler;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

//	@Autowired
//	private APIExceptionHandler _handler;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    // TODO: debug this
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
//		_handler.invalidJwtAuthenticationException(authException, request);
		System.out.println("HERE");
		resolver.resolveException(request, response, null, authException);

	}


}
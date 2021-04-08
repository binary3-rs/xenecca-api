package com.xenecca.api.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.xenecca.api.exception.APIException;
import com.xenecca.api.exception.ExceptionResponse;
import com.xenecca.api.exception.InvalidJwtAuthenticationException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(prefix = "_")
@Getter
@Slf4j
@Service
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider _tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String token = getTokenProvider().resolveToken((HttpServletRequest) request);
		System.out.println("HERE");
		try {
			if (token != null) {

				getTokenProvider().validateToken(token);
				Authentication auth = getTokenProvider().getAuthentication(token);
				if (auth != null) {
					log.info("Authenticating user....");
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
				chain.doFilter(request, response);

			}

		} catch (InvalidJwtAuthenticationException e) {
			setErrorResponse(request, response, HttpStatus.UNAUTHORIZED, e);
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
			setErrorResponse(request, response, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}

	}

	public void setErrorResponse(HttpServletRequest request, HttpServletResponse response, HttpStatus status, Throwable ex) {
		response.setStatus(status.value());
		response.setContentType("application/json");
		ExceptionResponse apiError = new ExceptionResponse(new Date(), ex.getMessage(), request.getRequestURI());
		try {
			String json = apiError.convertToJson();
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
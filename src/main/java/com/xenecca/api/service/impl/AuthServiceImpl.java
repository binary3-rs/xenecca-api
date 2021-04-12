package com.xenecca.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.UserRepository;
import com.xenecca.api.dto.request.LoginDTO;
import com.xenecca.api.dto.response.LoginDataDTO;
import com.xenecca.api.model.User;
import com.xenecca.api.security.JwtTokenProvider;
import com.xenecca.api.service.AuthService;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;



@Accessors(prefix = "_")
@Getter
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository _userRepository;

	@Autowired
	private AuthenticationManager _authenticationManager;

	@Autowired
	private JwtTokenProvider _tokenProvider;

	@Autowired
	PasswordEncoder _passwordEncoder;

	@Override
	public User findUserByUsername(String username) {
		log.info("Fetching a user with the username = " + username);
		User user = getUserRepository().findBy_username(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));
		return user;
	}

	@Override
	public LoginDataDTO login(LoginDTO credentialsDto) {
		try {
			String username = credentialsDto.getUsername();
			String password = credentialsDto.getPassword();
			log.info("Authentication in progress.....");

			getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
			User user = findUserByUsername(username);
			String token = "";

			//roles = user.getStringRoles();
			String pwd = user.getPassword();
			if (getPasswordEncoder().matches(password, pwd)) {
				token = getTokenProvider().createToken(user.getId(), username);
			} else {
				throw new BadCredentialsException("Invalid username/password supplied!");
			}

			LoginDataDTO responseDto = new LoginDataDTO(username, token);
			return responseDto;
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}
}
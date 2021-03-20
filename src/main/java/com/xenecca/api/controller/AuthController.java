package com.xenecca.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.LoginDTO;
import com.xenecca.api.dto.response.LoginDataDTO;
import com.xenecca.api.service.AuthService;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@RestController
@RequestMapping(value = "/api/v1/login")
public class AuthController {

	@Autowired
	AuthService _authService;

	@PostMapping
	public LoginDataDTO signin(@Valid @RequestBody LoginDTO credentialsDto) {
		return getAuthService().login(credentialsDto);
	}

}
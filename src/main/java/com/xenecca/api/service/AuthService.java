package com.xenecca.api.service;

import com.xenecca.api.dto.request.LoginDTO;
import com.xenecca.api.dto.response.LoginDataDTO;
import com.xenecca.api.model.User;

public interface AuthService {
	public User findUserByUsername(String username);
	public LoginDataDTO  login(LoginDTO credentialsDto);

}

package com.xenecca.api.service;

import com.xenecca.api.dto.request.NewUserDTO;
import com.xenecca.api.model.User;

public interface UserService {
	public User addUser(NewUserDTO userDTO);

	public Iterable<User> getAllUsers();

}

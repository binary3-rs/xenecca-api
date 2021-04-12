package com.xenecca.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.UserRepository;
import com.xenecca.api.dto.request.NewUserDTO;
import com.xenecca.api.mapper.UserMapper;
import com.xenecca.api.model.User;
import com.xenecca.api.service.UserService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository _userRepository;

	@Autowired
	private UserMapper _userMapper;

	@Autowired
	private PasswordEncoder _passwordEncoder;

	@Override
	public User addUser(NewUserDTO userDTO) {
		User user = getUserMapper().mapToEntity(userDTO);
		user.setPassword(getPasswordEncoder().encode(user.getPassword()));
		return getUserRepository().save(user);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return getUserRepository().findAll();
	}

}

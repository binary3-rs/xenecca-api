package com.xenecca.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.CategoryRepository;
import com.xenecca.api.dao.UserRepository;
import com.xenecca.api.dto.request.NewUserDTO;
import com.xenecca.api.model.User;
import com.xenecca.api.service.UserService;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository _userRepository;

//	@Autowired
//	private UserMapper _userMapper;

//	@Autowired
//	private PasswordEncoder _passwordEncoder;

	@Override
	public User addUser(NewUserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> getAllUsers() {
		return getUserRepository().findAll();
	}

}

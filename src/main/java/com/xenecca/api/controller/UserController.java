package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.response.UserDTO;
import com.xenecca.api.mapper.UserMapper;
import com.xenecca.api.model.User;
import com.xenecca.api.service.UserService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	public UserService _userService;
	@Autowired
	public UserMapper _userMapper;

	@GetMapping
	public List<UserDTO> getAllUsers() {

		Iterable<User> users = getUserService().getAllUsers();
		return getUserMapper().mapToDTOList(users);

	}
}

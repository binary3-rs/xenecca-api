package com.xenecca.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewUserDTO;
import com.xenecca.api.dto.response.UserDTO;
import com.xenecca.api.mapper.UserMapper;
import com.xenecca.api.model.User;
import com.xenecca.api.service.UserService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	public UserService _userService;
	@Autowired
	public UserMapper _userMapper;

	
	@PostMapping
	public UserDTO addUser(@RequestBody @Valid NewUserDTO data) {
		return getUserMapper().mapToDTO(getUserService().addUser(data));
	}
	
	@GetMapping
	public List<UserDTO> getAllUsers() {

		Iterable<User> users = getUserService().getAllUsers();
		return getUserMapper().mapToDTOList(users);

	}
}

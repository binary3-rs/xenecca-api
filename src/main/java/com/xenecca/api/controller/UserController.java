package com.xenecca.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewUserDTO;
import com.xenecca.api.dto.response.UserDTO;
import com.xenecca.api.mapper.UserMapper;
import com.xenecca.api.service.UserService;

import io.swagger.annotations.ApiOperation;
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

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@ApiOperation(value = "Add new user.")
	public UserDTO addUser(@RequestBody @Valid NewUserDTO userData) {
		return getUserMapper().mapToDTO(getUserService().addUser(userData));
	}

	@GetMapping
	@ApiOperation(value = "Get all users.")
	public List<UserDTO> getAllUsers() {
		return getUserMapper().mapToDTOList(getUserService().getAllUsers());

	}
}

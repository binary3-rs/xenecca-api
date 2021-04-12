package com.xenecca.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(prefix = "_")
public class LoginDTO {
	@NotBlank(message="Username cannot be blank!")
	private String _username;
	@NotBlank(message="Password cannot be blank!")
	private String _password;
}

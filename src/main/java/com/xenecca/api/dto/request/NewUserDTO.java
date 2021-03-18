package com.xenecca.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
public class NewUserDTO {

	@NotBlank
	private String _firstName;
	@NotBlank
	private String _lastName;
	@NotBlank
	private String _username;
	@NotBlank
	private String _email;
	@NotBlank
	private String _password;
}

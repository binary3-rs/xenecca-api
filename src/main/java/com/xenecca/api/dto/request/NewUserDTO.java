package com.xenecca.api.dto.request;

import javax.validation.constraints.Email;
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
	@NotBlank(message = "Email must be provided. Cannot be blank!")
	@Email(message = "Invalid email format.")
	private String _email;
	@NotBlank(message = "Password cannot be blank!")
	private String _password;
}

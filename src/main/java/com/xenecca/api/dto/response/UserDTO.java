package com.xenecca.api.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
public class UserDTO {

	private Long _id;
	private String _firstName;
	private String _lastName;
	private String _username;
	private String _email;
}

package com.xenecca.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@AllArgsConstructor
public class LoginDataDTO {
	private String _username;
	private String _token;

}

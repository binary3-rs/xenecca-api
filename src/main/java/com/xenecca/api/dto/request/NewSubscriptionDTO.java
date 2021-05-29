package com.xenecca.api.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewSubscriptionDTO {
	@NotBlank(message = "Email cannot be blank. This field is required.")
	@Email(message = "Invalid email format.")
	private String _email;
}

package com.xenecca.api.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.xenecca.api.model.type.MessageSubject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewMessageDTO {

	@NotBlank(message = "Name cannot be blank. This is required field.")
	private String _name;

	@NotBlank(message = "Message content cannot be blank. This is required field.")
	private String _content;

	@NotBlank(message = "Email cannot be blank. This field is required.")
	@Email(message = "Invalid email format.")
	private String _email;

	@Builder.Default
	private MessageSubject _subject = MessageSubject.OTHER;

}

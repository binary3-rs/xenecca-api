package com.xenecca.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.xenecca.api.model.type.MessageStatus;
import com.xenecca.api.model.type.MessageSubject;

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
@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@NotBlank(message = "Email cannot be blank. This field is required.")
	@Email(message = "Invalid email format.")
	@Column(name = "email", nullable = false)
	private String _email;

	@NotBlank(message = "Message content cannot be blank. This field is required.")
	@Size(max = 10000, message = "Length of the the message must be between 1 and 1000.")
	@Column(name = "content", nullable = false)
	private String _content;

	@NotBlank(message = "Name cannot be blank. This field is required.")
	@Size(max = 50, message = "Length of the first name must be between 1 and 50.")
	@Column(name = "name", nullable = false)
	private String _name;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private MessageStatus _status = MessageStatus.UNREAD;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(name = "subject")
	private MessageSubject _subject = MessageSubject.OTHER;

}

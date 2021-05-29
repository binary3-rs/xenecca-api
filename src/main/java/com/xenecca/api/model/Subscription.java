package com.xenecca.api.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@NotBlank(message = "Email cannot be blank. This field is required.")
	@Email(message = "Invalid email format.")
	@Column(name = "email", unique = true, nullable = false)
	private String _email;

	@Builder.Default
	@Column(name = "unsubscribe_token", length = 36, unique = true, nullable = false)
	private String _unsubscribeToken = UUID.randomUUID().toString();
}

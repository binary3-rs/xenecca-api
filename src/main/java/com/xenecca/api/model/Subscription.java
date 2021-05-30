package com.xenecca.api.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.xenecca.api.model.type.SubscriptionType;

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
@Table(name = "subscription", uniqueConstraints = {
		@UniqueConstraint(name = "email_type_uk_idx", columnNames = { "email", "type" }) })
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@NotBlank(message = "Email cannot be blank. This field is required.")
	@Email(message = "Invalid email format.")
	@Column(name = "email", unique = true, nullable = false)
	private String _email;

	@Enumerated(value = EnumType.STRING)
	@Builder.Default
	@Column(name = "type")
	private SubscriptionType _type = SubscriptionType.COURSE;

	@Builder.Default
	@Column(name = "unsubscribe_token", length = 36, unique = true, nullable = false)
	private String _unsubscribeToken = UUID.randomUUID().toString();
}

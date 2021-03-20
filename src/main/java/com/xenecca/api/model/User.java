package com.xenecca.api.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table(name = "\"user\"")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@NotBlank(message = "First name cannot be blank. This is required field.")
	@Size(max = 30, message = "Length of the first name must be between 1 and 30.")
	@Column(name = "first_name", nullable = false)
	private String _firstName;

	@NotBlank(message = "Last name cannot be blank. This is required field.")
	@Size(max = 30, message = "Length of the last name must be between 1 and 30.")
	@Column(name = "last_name", nullable = false)
	private String _lastName;

	@NotBlank(message = "Username cannot be blank. This is required field.")
	@Size(max = 50, message = "Length of the last name must be between 1 and 50.")
	@Column(name = "username", unique = true, nullable = false)
	private String _username;

	@NotBlank(message = "Email cannot be blank. This is required field.")
	@Email(message = "Invalid email format.")
	@Column(name = "email", unique = true, nullable = false)
	private String _email;

	@Size(min = 6, max = 255, message = "Length of the password must be between 6 and 50.")
	@Column(name = "password", nullable = false)
	private String _password;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date _createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date _updatedAt;

	@Builder.Default
	@Column(name = "account_non_expired")
	private Boolean _accountNonExpired = true;

	@Builder.Default
	@Column(name = "account_non_locked")
	private Boolean _accountNonLocked = true;

	@Builder.Default
	@Column(name = "credentials_non_expired")
	private Boolean _credentialsNonExpired = true;

	@Builder.Default
	@Column(name = "enabled")
	private Boolean _enabled = true;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return _accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return _accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return _credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return _enabled;
	}

}

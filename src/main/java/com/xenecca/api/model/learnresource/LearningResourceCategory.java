package com.xenecca.api.model.learnresource;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.xenecca.api.model.type.LearningResourceDomain;
import com.xenecca.api.model.type.MaterialType;

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
@Entity
public class LearningResourceCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@NotBlank(message = "Category name must not be blank!")
	@Column(name = "name", unique = true, nullable = false, length = 50)
	private String _name;

	@Enumerated(EnumType.STRING)
	@Column(name = "domain")
	private LearningResourceDomain _domain;

	@CreationTimestamp
	@Column(name = "created_at")
	private Time _createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Time _updatedAt;
}

package com.xenecca.api.model.learnresource;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.xenecca.api.model.Category;
import com.xenecca.api.model.type.MaterialType;
import com.xenecca.api.model.type.ResourceType;

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
public class LearningResource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@NotBlank(message = "Name of the learning resource must not be blank!")
	@Column(name = "name", unique = true, nullable = false, length = 100)
	private String _name;

	// NOTE: this can be URL (online resource) or file path (if resource is file)
	@NotBlank(message = "Resource must not be blank!")
	@Column(name = "resource", unique = true, nullable = false, length = 500)
	private String _resource;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_category_id")
	private LearningResourceCategory _resourceCategory;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(name = "material_type")
	private MaterialType _materialType = MaterialType.URL;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(name = "resource_type")
	private ResourceType _resourceType = ResourceType.TUTORIAL;

	@CreationTimestamp
	@Column(name = "created_at")
	private Time _createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Time _updatedAt;

}

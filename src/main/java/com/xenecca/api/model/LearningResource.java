package com.xenecca.api.model;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.xenecca.api.model.types.MaterialType;
import com.xenecca.api.model.types.ResourceType;

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
	@Column(name = "resource", unique = true, nullable = false, length = 255)
	private String _resource;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(name = "material_type")
	private MaterialType _materialType = MaterialType.URL;

	@Enumerated(EnumType.STRING)
	@Column(name = "resource_type")
	private ResourceType _resourceType;

	@Builder.Default
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "learning_tag", joinColumns = @JoinColumn(name = "learning_resource_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> _tags = new HashSet<>();

	@CreationTimestamp
	@Column(name = "created_at")
	private Time _createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Time _updatedAt;

	public void addTag(Tag tag) {
		_tags.add(tag);
		tag.addResource(this);
	}

	public void removeTag(Tag tag) {
		_tags.remove(tag);
		tag.removeResource(this);
	}

}

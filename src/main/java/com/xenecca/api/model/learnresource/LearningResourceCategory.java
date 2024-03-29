package com.xenecca.api.model.learnresource;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.xenecca.api.model.type.LearningResourceDomain;

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
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
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

	@Builder.Default
	@OneToMany(mappedBy = "_resourceCategory", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<LearningResource> _resources = new HashSet<LearningResource>();

	@Builder.Default
	@Column(name = "tags", length = 300, columnDefinition = "varchar(300) default ''")
	private String _tags = "";

	@Builder.Default
	@Column(name = "logo", length = 300)
	private String _logo = null;

	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp _createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Timestamp _updatedAt;

	public LearningResourceCategory(String name, LearningResourceDomain domain) {
		_name = name;
		_domain = domain;
	}

	public LearningResourceCategory(String name, LearningResourceDomain domain, String tags, String logo) {
		_name = name;
		_domain = domain;
		_tags = tags;
		_logo = logo;
	}
}

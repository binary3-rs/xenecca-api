package com.xenecca.api.model.course;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Subcategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@NotBlank(message = "Subcategory name must not be blank!")
	@Column(name = "name", unique = true, nullable = false, length = 60)
	private String _name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category _category;

	@Builder.Default
	@OneToMany(mappedBy = "_subcategory")
	private Set<Course> _courses = new HashSet<Course>();

	@CreationTimestamp
	@Column(name = "created_at")
	private Time _createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Time _updatedAt;

	public void addCourse(Course course) {
		_courses.add(course);
	}

	public void setCategory(Category category) {
		_category = category;
		category.addSubcategory(this);
	}

}

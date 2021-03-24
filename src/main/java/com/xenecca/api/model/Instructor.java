package com.xenecca.api.model;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Instructor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@NotBlank(message = "Name must not be blank!")
	@Column(name = "full_name", unique = true, nullable = false, length = 100)
	private String _fullName;

	@Column(name = "bio", length = 1500)
	private String _bio;

	@Column(name = "job_title", length = 150)
	private String _jobTitle;

	@Column(name = "num_of_courses", columnDefinition = "integer default 0")
	private Integer _numOfCourses;

	@Column(name = "num_of_students", columnDefinition = "integer default 0")
	private Integer _numOfStudents;

	@Min(value = 0, message = "Rating must not less than 0!")
	@Max(value = 5, message = "Rating must not greater than 5!")
	@Column(name = "avg_rating", columnDefinition = "decimal(6,5) default 0.0")
	private Double _avgRating;

	@URL
	@Column(name = "original_image_url")
	private String _originalImageURL;

	@Column(name = "image_path")
	private String _imagePath;

	@Column(name = "udemy_id", unique = true, nullable = false)
	private Long _udemyId;

	@CreationTimestamp
	@Column(name = "created_at")
	private Time _createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Time _updatedAt;

	@Builder.Default
	@ManyToMany(mappedBy = "_instructors")
	private Set<Course> _courses = new HashSet<>();

}

package com.xenecca.api.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
public class CurriculumItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long _id;

	@NotBlank(message = "Curriculum section title must not be blank!")
	@Column(name = "title", nullable = false)
	private String _title;
	
	@Column(name = "item_type", length = 20)
	private String _itemType;

	@Builder.Default
	@Column(name = "udemy_lesson_id", unique=true, nullable=true)
	private Long _udemyLessonId = null;
	
	@Min(1)
	@Column(name = "index")
	private Integer _index;
	
	@Min(0)
	@Column(name = "section_index")
	private Integer _sectionIndex;

//	@Column(name = "is_section")
//	private Integer _section;

	@Min(0)
	@Column(name = "content_length")
	private Integer _contentLength;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course _course;

	@CreationTimestamp
	@Column(name = "created_at")
	private Time _createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Time _updatedAt;
}

package com.xenecca.api.dto.response;

import java.sql.Timestamp;

import com.xenecca.api.model.course.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
	private Long _id;
	private String _title;
	private CategoryDTO _category;
	private SubcategoryDTO _subcategory;
	private String _language;
	private String _description;
	private String _objectives;
	private String _headline;
	private String _poster;
	private String _requirements;
	private String _udemyUrl;
	private Timestamp _updatedAt;

	public CourseDTO(Course course) {
		_id = course.getId();
		_title = course.getTitle();
		_category = new CategoryDTO(course.getCategory());
		_subcategory = new SubcategoryDTO(course.getSubcategory());
		_language = course.getLanguage() != null ? course.getLanguage().getName() : null;
		_description = course.getDescription();
		_objectives = course.getObjectives();
		_headline = course.getHeadline();
		_poster = course.getPosterPath();
		_requirements = course.getRequirements();
		_udemyUrl = course.getUdemyUrl();
		_updatedAt = course.getUpdatedAt();
	}

}

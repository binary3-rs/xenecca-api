package com.xenecca.api.dto.response;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xenecca.api.model.course.Course;
import com.xenecca.api.model.elastic.CourseDoc;

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
@JsonIgnoreProperties("instructorMapper")
public class CoursePreviewDTO {

	private Long _id;

	private String _title;

	private String _headline;
	
	private String _slug;

	private String _poster;

	private String _originalPosterUrl;

	private Date _timeUpdated;

	public CoursePreviewDTO(CourseDoc doc) {
		_id = doc.getDocId();
		_slug = doc.getSlug();
		_title = doc.getTitle();
		_headline = doc.getHeadline();
		_poster = doc.getPoster();
		_originalPosterUrl = doc.getOriginalPosterUrl();
		_timeUpdated = doc.getUpdatedAt();
	}

	public CoursePreviewDTO(Course course) {
		_id = course.getId();
		_title = course.getTitle();
		_slug = course.getSlug();
		_headline = course.getHeadline();
		_poster = course.getPosterPath();
		_originalPosterUrl = course.getOriginalPosterUrl();
		_timeUpdated = course.getUpdatedAt();

	}

}

package com.xenecca.api.dto.response;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xenecca.api.model.Course;
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

	private String _poster;

	private String _originalPosterURL;

	private Date _timeUpdated;

	public CoursePreviewDTO(CourseDoc doc) {
		_id = doc.getDocId();
		_title = doc.getTitle();
		_headline = doc.getHeadline();
		_poster = doc.getPoster();
		_originalPosterURL = doc.getOriginalPosterURL();
		_timeUpdated = doc.getUpdatedAt();
	}

	public CoursePreviewDTO(Course course) {
		_id = course.getId();
		_title = course.getTitle();
		_headline = course.getHeadline();
		_poster = course.getPosterPath();
		_originalPosterURL = course.getOriginalPosterURL();
		_timeUpdated = course.getUpdatedAt();

	}

}

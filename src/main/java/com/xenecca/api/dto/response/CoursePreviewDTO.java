package com.xenecca.api.dto.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xenecca.api.es.models.CourseDoc;
import com.xenecca.api.mapper.InstructorMapper;
import com.xenecca.api.model.Course;

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
	private String _badge;

	private String _title;

	private String _headline;

	private Double _avgRating;

	private Double _price;

	private Double _oldPrice;

	private String _priceAsString;

	private List<InstructorDTO> _instructors;

	private String _poster;

	private String _originalPosterURL;

	private Integer _numOfStudents;

	private Integer _numOfReviews;

	private Date _timeUpdated;

	private String _videoContentLength;
	private InstructorMapper _instructorMapper = new InstructorMapper();

	public CoursePreviewDTO(CourseDoc doc) {
		_id = doc.getDocId();
		_badge = doc.getBadge();
		_title = doc.getTitle();
		_headline = doc.getHeadline();
		_avgRating = doc.getAvgRating();
		_price = doc.getPrice();
		_oldPrice = doc.getOldPrice();
		_priceAsString = doc.getPriceAsString();
		_instructors = getInstructorMapper().mapDocsToDTOList(doc.getInstructors());
		_poster = doc.getPoster();
		_originalPosterURL = doc.getOriginalPosterURL();
		_numOfStudents = doc.getNumOfStudents();
		_numOfReviews = doc.getNumOfReviews();
		_timeUpdated = doc.getUpdatedAt();
		_videoContentLength = doc.getDurationInHrs() + " hours";

	}

	public CoursePreviewDTO(Course course) {
		_id = course.getId();
		_title = course.getTitle();
		_headline = course.getHeadline();
		_avgRating = course.getAvgRating();
		_badge = course.getBadge();
		_price = course.getPrice();
		_oldPrice = course.getOldPrice();
		_priceAsString = course.getPrice().toString() + course.getCurrency();
		_instructors = getInstructorMapper().mapToDTOList(course.getInstructors());
		_poster = course.getPosterPath();
		_originalPosterURL = course.getOriginalPosterURL();
		_numOfStudents = course.getNumOfStudents();
		_numOfReviews = course.getNumOfReviews();
		_timeUpdated = course.getUpdatedAt();
		_videoContentLength = course.getVideoContentLength();

	}

}

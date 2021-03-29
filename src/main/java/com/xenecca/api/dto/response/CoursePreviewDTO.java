package com.xenecca.api.dto.response;

import java.util.Date;
import java.util.List;

import com.xenecca.api.es.models.CourseDoc;
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
public class CoursePreviewDTO {

	private Long _id;

	private String _title;

	private String _headline;

	private Double _avgRating;

	private CategoryDTO _category;

	private SubcategoryDTO _subcategory;

	private TopicDTO _topic;

	private LanguageDTO _language;

	private Double _price;

	private String _priceAsString;

	private List<InstructorDTO> _instructors;

	private String _poster;

	private String _originalPosterURL;

	private Integer _numOfStudents;

	private Integer _numOfReviews;

	private Date _timeUpdated;

	private String _videoContentLength;

	public CoursePreviewDTO(CourseDoc doc) {
		_id = doc.getDocId();
		_title = doc.getTitle();
		_headline = doc.getHeadline();
		_avgRating = doc.getAvgRating();
		// _category = new CategoryDTO(doc.getCategory().get)
		_subcategory = null;
		_topic = null;
		_language = null;
		_price = doc.getPrice();
		_priceAsString = doc.getPriceAsString();
		_instructors = null;
		_poster = doc.getPoster();
		_originalPosterURL = doc.getOriginalPosterURL();
		_numOfStudents = doc.getNumOfStudents();
		_numOfReviews = doc.getNumOfReviews();
		_timeUpdated = doc.getTimeUpdated();
		_videoContentLength = doc.getVideoContentLength();

	}
	
	
	public CoursePreviewDTO(Course course) {
		_id = course.getId();
		_title = course.getTitle();
		_headline = course.getHeadline();
		_avgRating = course.getAvgRating();
		// _category = new CategoryDTO(doc.getCategory().get)
		_subcategory = null;
		_topic = null;
		_language = null;
		_price = course.getPrice();
		_priceAsString = course.getPrice().toString() + course.getCurrency();
		_instructors = null;
		_poster = course.getPosterPath();
		_originalPosterURL = course.getOriginalPosterURL();
		_numOfStudents = course.getNumOfStudents();
		_numOfReviews = course.getNumOfReviews();
		_timeUpdated = course.getUpdatedAt();
		_videoContentLength = course.getVideoContentLength();

	}

}

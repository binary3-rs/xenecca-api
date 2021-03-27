package com.xenecca.api.dto.response;

import java.util.Date;
import java.util.List;

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

	private Long _docId;

	private String _title;

	private String _headline;

	private Double _avgRating;

	private Long _category;

	private Long _subcategory;

	private Long _topic;

	private Long _language;

	private Double _price;

	private Double _priceAsString;

	private List<InstructorDTO> _instructors;

	private String _image;

	private String _originalImageUrl;

	private String _studentsEnrolled;

	private String _numOfReviews;

	private String _discountPeriod;

	private Date _timeAdded;

}

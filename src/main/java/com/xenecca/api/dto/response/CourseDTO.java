package com.xenecca.api.dto.response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xenecca.api.mapper.CurriculumItemMapper;
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
public class CourseDTO {
	private Long _id;
	private String _title;
	private CategoryDTO _category;
	private SubcategoryDTO _subcategory;
	private TopicDTO _topic;
	private String _language;
	private Double _avgRating;
	private String _badge;
	private Boolean _hasCertificate;
	private List<CurriculumSectionDTO> _curriculumItems;
	private String _description;
	private String _devicesAccess;
	private Integer _discountPercent;
	private String _videoContentLength;
	private String _goals;
	private String _headline;
	private String _poster;
	private List<InstructorDTO> _instructors = new ArrayList<InstructorDTO>();
	private Boolean _hasLifetimeAccess;
	private Integer _numOfArticles;
	private Integer _numOfReviews;
	private String _originalPosterURL;
	private Double _oldPrice;
	private Double _price;
	private String _requirements;
	private Integer _numOfStudents;
	private String _udemyURL;
	private Map<String, Integer> _ratings;
	private Timestamp _updatedAt;

	public CourseDTO(Course course) {
		_id = course.getId();
		_title = course.getTitle();
		_category = new CategoryDTO(course.getCategory());
		_subcategory = new SubcategoryDTO(course.getSubcategory());
		_topic = new TopicDTO(course.getTopic());
		_language = course.getLanguage() != null ? course.getLanguage().getName() : null;
		_avgRating = course.getAvgRating();
		_badge = course.getBadge();
		_hasCertificate = course.getCertificate();
		_curriculumItems = CurriculumItemMapper.mapToCurriculumDTO(course.getCurriculumItems());
		_description = course.getDescription();
		_devicesAccess = course.getDevicesAccess();
		_discountPercent = 100; // hardcoded
		_videoContentLength = course.getVideoContentLength();
		_goals = course.getGoals();
		_headline = course.getHeadline();
		_poster = course.getPosterPath();
		course.getInstructors().forEach(instructor -> _instructors.add(new InstructorDTO(instructor)));;
		_hasLifetimeAccess = course.getLifetimeAccess();
		_numOfArticles = course.getNumOfArticles();
		_numOfReviews = course.getNumOfReviews();
		_originalPosterURL = course.getOriginalPosterURL();
		_oldPrice = course.getOldPrice();
		_price = course.getPrice();
		_requirements = course.getRequirements();
		_numOfStudents = course.getNumOfStudents();
		_udemyURL = course.getUdemyURL();
		_ratings = Map.of("rating1", course.getRatingCount1(), "rating2", course.getRatingCount2(), "rating3",
				course.getRatingCount3(), "rating4", course.getRatingCount4(), "rating5", course.getRatingCount5());
		_updatedAt = course.getUpdatedAt();
	}

}

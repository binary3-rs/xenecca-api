package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.response.CourseDTO;
import com.xenecca.api.dto.response.CoursePreviewDTO;
import com.xenecca.api.mapper.CoursePreviewMapper;
import com.xenecca.api.model.Course;
import com.xenecca.api.service.CourseService;
import com.xenecca.api.service.SearchService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@RestController
@RequestMapping("/api/v1/courses/")
public class CourseController {

	@Autowired
	private CourseService _courseService;

	@Autowired
	private SearchService _searchService;

	@Autowired
	private CoursePreviewMapper _coursePreviewMapper;

	@GetMapping
	public List<CoursePreviewDTO> searchCourses(@RequestParam(name = "q", required = false) String searchTerm,
			@RequestParam(name = "category", required = false) Integer categoryId,
			@RequestParam(value = "subcategory", required = false) Integer subcategoryId,
			@RequestParam(value = "language", required = false) Integer languageId,
			@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(name = "sort", defaultValue = "date_added") String sortBy,
			@RequestParam(name = "order", defaultValue = "desc") String order) {
		if (searchTerm == null && categoryId == null && subcategoryId == null && languageId == null) {
			Iterable<Course> courses = getCourseService().getAllCourses(pageNo, sortBy, order, null);
			return getCoursePreviewMapper().mapCoursesToDTOList(courses);
		}
		return getCoursePreviewMapper().mapDocToDTOList(getSearchService().searchCourses(searchTerm, categoryId,
				subcategoryId, languageId, pageNo, sortBy, order));
	}

	@PutMapping("{id}")
	public CourseDTO getCourseById(@PathVariable("id") Long courseId) {
		Course course = getCourseService().getCourseById(courseId);
		return new CourseDTO(course);

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("{id}/redeem-coupon")
	public void redeemCoupon(@PathVariable("id") Long courseId) {
		getCourseService().redeemCourseCoupon(courseId);

	}

	@GetMapping("{id}/similar")
	public List<CoursePreviewDTO> getSimilarCourses(@PathVariable("id") Long courseId,
			@RequestParam(name = "num", defaultValue = "4") Integer numOfCourses) {
		return getCoursePreviewMapper()
				.mapCoursesToDTOList(getCourseService().getSimilarCourses(courseId, numOfCourses));
	}

	@GetMapping("recommend")
	public List<CoursePreviewDTO> recommendTopCourses(
			@RequestParam(name = "num", defaultValue = "8") Integer numOfCourses) {
		return getCoursePreviewMapper().mapCoursesToDTOList(getCourseService().recommendTopCourses(numOfCourses));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void deleteCourse(@PathVariable("id") Long courseId) {

		// delete course instance
		getCourseService().deleteCourseById(courseId);
		// delete course document
		getSearchService().deleteCourseDocument(courseId);

	}

}

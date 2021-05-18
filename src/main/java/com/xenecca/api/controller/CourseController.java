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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
	@ApiOperation(value = "Get (search) available courses")
	public List<CoursePreviewDTO> searchCourses(
			@ApiParam(name = "q", type = "String", value = "Search term", example = "java", required = false) @RequestParam(name = "q", required = false) String searchTerm,
			@RequestParam(name = "category", required = false) Integer categoryId,
			@RequestParam(value = "subcategory", required = false) Integer subcategoryId,
			@RequestParam(value = "language", required = false) Integer languageId,
			@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			@ApiParam(name = "sort", type = "String", value = "Sorting field", allowableValues = "date_added, popularity, name", example = "name", required = false) 
			@RequestParam(name = "sort", defaultValue = "date_added") String sortBy,
			@ApiParam(name = "order", type = "String", value = "Sorting order", allowableValues = "asc, desc", example = "asc", required = false) @RequestParam(name = "order", defaultValue = "desc") String order) {
		if (searchTerm == null && categoryId == null && subcategoryId == null && languageId == null) {
			Iterable<Course> courses = getCourseService().getAllCourses(pageNo, sortBy, order, null);
			return getCoursePreviewMapper().mapCoursesToDTOList(courses);
		}
		return getCoursePreviewMapper().mapDocToDTOList(getSearchService().searchCourses(searchTerm, categoryId,
				subcategoryId, languageId, pageNo, sortBy, order));
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Get a course (by id)")
	public CourseDTO getCourseById(@PathVariable("id") Long courseId) {
		Course course = getCourseService().getCourseById(courseId);
		return new CourseDTO(course);

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("{id}/redeem-coupon")
	@ApiOperation(value = "Update used coupons count")
	public void redeemCoupon(@PathVariable("id") Long courseId) {
		getCourseService().redeemCourseCoupon(courseId);

	}

	@GetMapping("{id}/similar")
	@ApiOperation(value = "Get similar courses")
	public List<CoursePreviewDTO> getSimilarCourses(@PathVariable("id") Long courseId,
			@ApiParam(name = "num", type = "Integer", value = "Number of desired courses", example = "6",required = false)
			@RequestParam(name = "num", defaultValue = "4") Integer numOfCourses) {
		return getCoursePreviewMapper()
				.mapCoursesToDTOList(getCourseService().getSimilarCourses(courseId, numOfCourses));
	}

	@GetMapping("recommend")
	@ApiOperation(value = "Get recommended courses")
	public List<CoursePreviewDTO> recommendTopCourses(
			@ApiParam(name = "num", type = "Integer", value = "Number of desired courses", example = "6",required = false)
			@RequestParam(name = "num", defaultValue = "8") Integer numOfCourses) {
		return getCoursePreviewMapper().mapCoursesToDTOList(getCourseService().recommendTopCourses(numOfCourses));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	@ApiOperation(value = "Delete course")
	public void deleteCourse(@PathVariable("id") Long courseId) {

		// delete course instance
		getCourseService().deleteCourseById(courseId);
		// delete course document
		getSearchService().deleteCourseDocument(courseId);

	}

}

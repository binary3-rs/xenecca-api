package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.response.CourseDTO;
import com.xenecca.api.dto.response.CoursePreviewDTO;
import com.xenecca.api.es.models.CourseDoc;
import com.xenecca.api.mapper.CourseMapper;
import com.xenecca.api.mapper.CoursePreviewMapper;
import com.xenecca.api.model.Course;
import com.xenecca.api.service.CourseService;
import com.xenecca.api.service.SearchService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@RestController
@RequestMapping("/api/v1/courses/")
public class CourseController {

	@Autowired
	private CourseService _courseService;

	@Autowired
	private SearchService _searchService;

	@Autowired
	private CourseMapper _courseMapper;

	@Autowired
	private CoursePreviewMapper _coursePreviewMapper;

//	@GetMapping
//	public List<CourseDTO> getAllCourses(@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
//			@RequestParam(value = "pageSize", defaultValue = "18") Integer pageSize) {
//		Iterable<Course> courses = getCourseService().getAllCourses(pageNo, pageSize);
//		return getCourseMapper().mapToDTOList(courses);
//
//	}

	@GetMapping
	public List<CoursePreviewDTO> searchCourses(@RequestParam(name = "q", required = false) String searchTerm,
			@RequestParam(name = "category", required = false) Integer categoryId,
			@RequestParam(value = "subcategory", required = false) Integer subcategoryId,
			@RequestParam(value = "topic", required = false) Integer topicId,
			@RequestParam(value = "language", required = false) Integer languageId,
			@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(name = "sort", defaultValue = "date_added") String sortBy,
			@RequestParam(name = "order", defaultValue = "desc") String order) {

		if (searchTerm == null && categoryId == null && subcategoryId == null && topicId == null
				&& languageId == null) {
			System.out.println("DEBUG");
			Iterable<Course> courses = getCourseService().getAllCourses(pageNo, sortBy, order);
			return getCoursePreviewMapper().mapCoursesToDTOList(courses);
		}

		return getCoursePreviewMapper().mapDocToDTOList(getSearchService().searchCourses(searchTerm, categoryId,
				subcategoryId, topicId, languageId, null, null, pageNo, sortBy, order));
	}

	@GetMapping("{id}")
	public CourseDTO getCourseById(@PathVariable("id") Long courseId) {
		Course course = getCourseService().getCourseById(courseId);
		// return getCourseMapper().mapToDTO(course);
		return new CourseDTO(course);

	}

	@GetMapping("test")
	public boolean test() {
		return getSearchService().addDocument();

	}

}

package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.response.CourseDTO;
import com.xenecca.api.mapper.CourseMapper;
import com.xenecca.api.model.Course;
import com.xenecca.api.service.CourseService;

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
	private CourseMapper _courseMapper;

	@GetMapping
	public List<CourseDTO> getAllCourses(@RequestParam(name="pageNo", defaultValue="0") Integer pageNo, @RequestParam(value="pageSize", defaultValue="18") Integer pageSize) {
		Iterable<Course> courses = getCourseService().getAllCourses(pageNo, pageSize);
		return getCourseMapper().mapToDTOList(courses);

	}

	@GetMapping("{id}")
	public CourseDTO getCourseById(@PathVariable("id") Long courseId) {
		Course course = getCourseService().getCourseById(courseId);
		return getCourseMapper().mapToDTO(course);

	}

}

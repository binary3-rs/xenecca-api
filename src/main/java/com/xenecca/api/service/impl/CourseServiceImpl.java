package com.xenecca.api.service.impl;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.CourseRepository;
import com.xenecca.api.model.Course;
import com.xenecca.api.service.CourseService;
import com.xenecca.api.utils.FileUtils;
import com.xenecca.api.utils.SortAndCompareUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository _courseRepository;

	@Override
	public Iterable<Course> getAllCourses(Integer pageNo, String sortBy, String order) {
		Pageable sortedPageable = SortAndCompareUtils.createPageable(pageNo, sortBy, order);
		Page<Course> pageOfCourses = getCourseRepository().findAll(sortedPageable);
		return pageOfCourses.getContent();

	}

	@Override
	public Course getCourseById(Long courseId) {
		return getCourseRepository().findById(courseId).get();
	}

	@Override
	public void deleteCourseById(Long courseId) {
		Course course = getCourseRepository().findById(courseId).get();
		String posterPath = course.getPosterPath();
		// delete course instance
		getCourseRepository().delete(course);

		if (posterPath != null) {
			FileUtils.deleteFile(Paths.get(posterPath));
		}
	}

}

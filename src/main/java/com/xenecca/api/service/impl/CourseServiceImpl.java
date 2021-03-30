package com.xenecca.api.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.CourseRepository;
import com.xenecca.api.model.Course;
import com.xenecca.api.service.CourseService;
import com.xenecca.api.utils.SortAndCompareUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository _courseRepository;


	@Override
	public Iterable<Course> getAllCourses(Integer pageNo, String sortBy, String order) {
		//String sortByField = SortAndCompareUtils.sortField(sortBy);
		//Sort sort = order.equals("asc") ? Sort.by(sortByField).ascending() : Sort.by(sortByField).descending();
		Pageable sortedPageable = SortAndCompareUtils.createPageable(pageNo, sortBy, order); 
		//PageRequest.of(pageNo, PAGE_SIZE, sort);
		Page<Course> pageOfCourses = getCourseRepository().findAll(sortedPageable);
		return pageOfCourses.getContent();

	}

	@Override
	public Course getCourseById(Long courseId) {
		return getCourseRepository().findById(courseId).get();
	}

}

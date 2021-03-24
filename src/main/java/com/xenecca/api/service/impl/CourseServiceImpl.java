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

	@Autowired
	private SessionFactory sessionFactory;
	
	private final int PAGE_SIZE = 18;

	@Override
	public Iterable<Course> getAllCourses(Integer pageNo, Integer pageSize) {
		
		//return getCourseRepository().findAll();
		Pageable sortedByDateAddedDesc = PageRequest.of(pageNo, pageSize, Sort.by("_createdAt").descending());
		Page<Course> pageOfCourses = getCourseRepository().findAll(sortedByDateAddedDesc);
		return pageOfCourses.getContent();
		
	}

	@Override
	public Course getCourseById(Long courseId) {
		return getCourseRepository().findById(courseId).get();
	}

	@Override
	public Iterable<Course> getAllCoursesByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Course> getAllCoursesBySubcategoryId(Long subcategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Course> getAllCoursesByTopicId(Long topicId) {
		// TODO Auto-generated method stub
		return null;
	}

}

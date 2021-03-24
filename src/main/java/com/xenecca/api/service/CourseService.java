package com.xenecca.api.service;

import com.xenecca.api.model.Course;

public interface CourseService {

	public Iterable<Course> getAllCourses(Integer pageNo, Integer pageSize);
	
	public Course getCourseById(Long courseId);

	public Iterable<Course> getAllCoursesByCategoryId(Long categoryId);

	public Iterable<Course> getAllCoursesBySubcategoryId(Long subcategoryId);

	public Iterable<Course> getAllCoursesByTopicId(Long topicId);

}

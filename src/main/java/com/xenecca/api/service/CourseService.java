package com.xenecca.api.service;

import com.xenecca.api.model.Course;

public interface CourseService {

	public Iterable<Course> getAllCourses(Integer pageNo, String sortBy, String order);
	
	public Course getCourseById(Long courseId);
	
	public void deleteCourseById(Long courseId);

}

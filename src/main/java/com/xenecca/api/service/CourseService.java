package com.xenecca.api.service;

import com.xenecca.api.model.Course;

public interface CourseService {

	public Iterable<Course> getAllCourses(Integer pageNo, String sortBy, String order, Integer size);

	public Course getCourseById(Long courseId);

	public Iterable<Course> getSimilarCourses(Long courseId, Integer numOfCourses);
	
	public Iterable<Course> recommendTopCourses(Integer numOfCourses);

	public void redeemCourseCoupon(Long courseId);

	public void deleteCourseById(Long courseId);

}

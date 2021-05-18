package com.xenecca.api.service;

import com.xenecca.api.model.Course;
import com.xenecca.api.utils.model.PageResult;

public interface CourseService {

	public PageResult<Course> getAllCourses(Integer pageNo, Integer pageSize);

	public Course getCourseById(Long courseId);

	public Iterable<Course> getSimilarCourses(Long courseId, Integer numOfCourses);

	public Iterable<Course> recommendTopCourses(Integer numOfCourses);

	public void redeemCourseCoupon(Long courseId);

	public void deleteCourseById(Long courseId);

}

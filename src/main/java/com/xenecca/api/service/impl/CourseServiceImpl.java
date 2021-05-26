package com.xenecca.api.service.impl;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.CourseRepository;
import com.xenecca.api.model.Course;
import com.xenecca.api.service.CourseService;
import com.xenecca.api.utils.FileUtils;
import com.xenecca.api.utils.SortAndCompareUtils;
import com.xenecca.api.utils.model.PageResult;

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
	@Cacheable(cacheNames = "courses")
	public PageResult<Course> getAllCourses(Integer pageNo, Integer pageSize) {
		Page<Course> pageOfCourses = _getAllCourses(pageNo, pageSize, "date", "desc");
		return new PageResult<Course>(pageOfCourses.getContent(), pageOfCourses.getTotalElements(), pageSize);

	}

	@Override
	public Course getCourseById(Long courseId) {
		return getCourseRepository().findById(courseId).get();
	}

	@Override
	public Iterable<Course> getSimilarCourses(Long courseId, Integer numOfCourses) {
		Course course = getCourseById(courseId);
		Pageable pageable = SortAndCompareUtils.createPageable(0, numOfCourses, "popularity", "desc");
		Page<Course> pageOfCourses = getCourseRepository().findBySubcategoryIdExcludeCourse(course.getId(),
				course.getSubcategory().getId(), pageable);
		List<Course> courses = new ArrayList<>(pageOfCourses.getContent());
		if (courses.size() < numOfCourses) {
			pageable = SortAndCompareUtils.createPageable(0, numOfCourses - courses.size(), "popularity", "desc");
			List<Course> coursesFetchedByCategory = getCourseRepository()
					.findOnlyByCategoryId(course.getCategory().getId(), course.getSubcategory().getId(), pageable)
					.getContent();
			courses.addAll(coursesFetchedByCategory);

		}
		return courses;
	}

	@Override
	public void redeemCourseCoupon(Long courseId) {
		getCourseRepository().updateReedemedCouponCount(courseId);

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

	@Override
	public Iterable<Course> recommendTopCourses(Integer numOfCourses) {
		return _getAllCourses(0, numOfCourses, "popularity", "desc").getContent();
	}

	private Page<Course> _getAllCourses(Integer pageNo, Integer pageSize, String sortBy, String order) {
		Pageable sortedPageable = SortAndCompareUtils.createPageable(pageNo, pageSize, sortBy, order);
		return getCourseRepository().findAll(sortedPageable);
	}
}

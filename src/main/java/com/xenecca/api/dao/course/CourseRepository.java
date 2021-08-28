package com.xenecca.api.dao.course;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.course.Course;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

	Page<Course> findAllBy_category__id(Long id, Pageable pageable);

	Page<Course> findAllBy_subcategory__id(Long id, Pageable pageable);
	
	Optional<Course> findBy_slug(String slug);

	@Modifying
	@Transactional
	@Query("UPDATE Course c SET c._redeemedCouponCount=c._redeemedCouponCount + 1 WHERE" + " c._id= :courseId")
	void updateReedemedCouponCount(@Param("courseId") Long courseId);

	@Query("SELECT c FROM Course c WHERE c._subcategory._id=:subcategoryId AND c._id!= :courseId")
	Page<Course> findBySubcategoryIdExcludeCourse(@Param("courseId") Long courseId,
			@Param("subcategoryId") Long subcategoryId, Pageable pageable);

	@Query("SELECT c FROM Course c WHERE c._category._id=:categoryId AND c._subcategory._id!= :subcategoryId")
	Page<Course> findOnlyByCategoryId(@Param("categoryId") Long categoryId, @Param("subcategoryId") Long subcategoryId,
			Pageable pageable);

}

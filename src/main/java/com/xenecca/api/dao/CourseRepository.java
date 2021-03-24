package com.xenecca.api.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.Course;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
	/*
	 * // @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true")
	 * })
	 * 
	 * @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true"),
	 * 
	 * @QueryHint(name = "org.hibernate.cacheRegion", value =
	 * "com.xenecca.api.model.Course") }) //@Cacheable(key="#p1") Page<Course>
	 * findAll(Pageable pageable);
	 */

	List<Course> findAllBy_category__id(Long id, Pageable pageable);

	List<Course> findAllBy_subcategory__id(Long id, Pageable pageable);

	List<Course> findAllBy_topic__id(Long id, Pageable pageable);

}

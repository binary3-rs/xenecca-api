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

	List<Course> findAllBy_category__id(Long id, Pageable pageable);

	List<Course> findAllBy_subcategory__id(Long id, Pageable pageable);

	List<Course> findAllBy_topic__id(Long id, Pageable pageable);

}

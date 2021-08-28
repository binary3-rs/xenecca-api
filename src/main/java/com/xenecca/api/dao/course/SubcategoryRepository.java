package com.xenecca.api.dao.course;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.course.Subcategory;

@Repository
public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {

	Iterable<Subcategory> findBy_category__id(Long categoryId);
	Iterable<Subcategory> findBy_category__name(String categoryName);
}

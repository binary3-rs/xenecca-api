package com.xenecca.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.Subcategory;

@Repository
public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {

	public Iterable<Subcategory> findBy_category__id(Long categoryId);
	public Iterable<Subcategory> findBy_category__name(String categoryName);
}

package com.xenecca.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.CategoryRepository;
import com.xenecca.api.dao.SubcategoryRepository;
import com.xenecca.api.dto.request.NewSubcategoryDTO;
import com.xenecca.api.model.course.Category;
import com.xenecca.api.model.course.Subcategory;
import com.xenecca.api.service.SubcategoryService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Service
public class SubcategoryServiceImpl implements SubcategoryService {

	@Autowired
	private CategoryRepository _categoryRepository;

	@Autowired
	private SubcategoryRepository _subcategoryRepository;

	@Override
	public Subcategory addSubcategory(Long categoryId, NewSubcategoryDTO subcategoryData) throws Exception {
		Optional<Category> result = getCategoryRepository().findById(categoryId);
		if (result.isEmpty()) {
			throw new Exception("Category not found!");
		}
		Subcategory subcategory = Subcategory.builder().name(subcategoryData.getName()).build();
		subcategory.setCategory(result.get());
		return getSubcategoryRepository().save(subcategory);
	}

	@Override
	@Cacheable(cacheNames = "subcategories")
	public Iterable<Subcategory> getAllSubcategories() {
		return getSubcategoryRepository().findAll();
	}

	@Override
	@Cacheable(cacheNames = "subcategories")
	public Iterable<Subcategory> getSubcategoriesByCategory(Long categoryId) {
		return getSubcategoryRepository().findBy_category__id(categoryId);
	}

}

package com.xenecca.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.CategoryRepository;
import com.xenecca.api.dao.SubcategoryRepository;
import com.xenecca.api.dto.request.NewSubcategoryDTO;
import com.xenecca.api.model.Category;
import com.xenecca.api.model.Subcategory;
import com.xenecca.api.service.SubcategoryService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
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
	public Iterable<Subcategory> getAllSubcategories() {
		return getSubcategoryRepository().findAll();
	}

	@Override
	public Iterable<Subcategory> getSubcategoriesByCategory(Long categoryId) {
		return getSubcategoryRepository().findBy_category__id(categoryId);
	}

}

package com.xenecca.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.CategoryRepository;
import com.xenecca.api.dto.request.NewCategoryDTO;
import com.xenecca.api.model.Category;
import com.xenecca.api.service.CategoryService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository _categoryRepository;

	@Override
	public Category addCategory(NewCategoryDTO categoryData) {
		Category category = Category.builder().name(categoryData.getName()).build();
		return getCategoryRepository().save(category);
	}
	
	
	@Override
	@Cacheable(cacheNames = "categories")
	public Iterable<Category> getAllCategories() {
		return getCategoryRepository().findAll();
	}

}

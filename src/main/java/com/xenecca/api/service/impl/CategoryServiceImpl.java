package com.xenecca.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.CategoryRepository;
import com.xenecca.api.dto.request.NewCategoryDTO;
import com.xenecca.api.model.course.Category;
import com.xenecca.api.service.CategoryService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
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
	public Iterable<Category> getAllCategories() {
		return getCategoryRepository().findAll();
	}

}

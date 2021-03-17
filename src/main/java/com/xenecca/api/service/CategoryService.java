package com.xenecca.api.service;

import com.xenecca.api.dto.request.NewCategoryDTO;
import com.xenecca.api.model.Category;

public interface CategoryService {
	
	public Category addCategory(NewCategoryDTO categoryData);
	
	public Iterable<Category> getAllCategories();

}

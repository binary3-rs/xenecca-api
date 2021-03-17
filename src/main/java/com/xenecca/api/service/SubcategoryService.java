package com.xenecca.api.service;

import com.xenecca.api.dto.request.NewSubcategoryDTO;
import com.xenecca.api.model.Subcategory;

public interface SubcategoryService {
	
	public Subcategory addSubcategory(Long categoryId, NewSubcategoryDTO subcategoryData) throws Exception;
	
	public Iterable<Subcategory> getSubcategoriesForCategory(Long categoryId);

}

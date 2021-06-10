package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.response.SubcategoryDTO;
import com.xenecca.api.mapper.SubcategoryMapper;
import com.xenecca.api.service.SubcategoryService;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@RestController
public class SubcategoryController {

	@Autowired
	private SubcategoryService _subcategoryService;

	@Autowired
	private SubcategoryMapper _subcategoryMapper;

	// NOTE: by default 0 is used as key
	@Cacheable(value = "subcategories", key = "#root.method", sync = true)
	@GetMapping("/v1/subcategories/")
	@ApiOperation(value = "Get all subcategories.")
	public List<SubcategoryDTO> getAllSubcategories() {
		return getSubcategoryMapper().mapToDTOList(getSubcategoryService().getAllSubcategories());
	}

	@Cacheable(cacheNames = "subcategories", sync = true)
	@GetMapping("/v1/categories/{id}/subcategories/")
	@ApiOperation(value = "Get all subcategories by category.")
	public List<SubcategoryDTO> getSubcategoriesByCategory(@PathVariable("id") Long categoryId) {
		return getSubcategoryMapper().mapToDTOList(getSubcategoryService().getSubcategoriesByCategory(categoryId));
	}

}

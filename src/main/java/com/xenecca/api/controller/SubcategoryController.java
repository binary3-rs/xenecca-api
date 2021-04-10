package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.response.SubcategoryDTO;
import com.xenecca.api.mapper.SubcategoryMapper;
import com.xenecca.api.service.SubcategoryService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@RestController
//@RequestMapping("/api/v1/categories")
public class SubcategoryController {

	@Autowired
	private SubcategoryService _subcategoryService;

	@Autowired
	private SubcategoryMapper _subcategoryMapper;

	@GetMapping("/api/v1/subcategories")
	public List<SubcategoryDTO> getAllSubcategories() {
		return getSubcategoryMapper().mapToDTOList(getSubcategoryService().getAllSubcategories());
	}
	
	@GetMapping("/api/v1/categories/{id}/subcategories")
	public List<SubcategoryDTO> getSubcategoriesForCategory(@PathVariable("id") Long categoryId) {
		return getSubcategoryMapper().mapToDTOList(getSubcategoryService().getSubcategoriesByCategory(categoryId));
	}

}

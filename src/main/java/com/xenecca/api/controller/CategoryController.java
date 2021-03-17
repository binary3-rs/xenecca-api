package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewCategoryDTO;
import com.xenecca.api.dto.response.CategoryDTO;
import com.xenecca.api.mapper.CategoryMapper;
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
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService _categoryService;

	@Autowired
	private CategoryMapper _categoryMapper;

	@PostMapping
	public CategoryDTO addCategory(@RequestBody NewCategoryDTO data) {
		return getCategoryMapper().mapToDTO(getCategoryService().addCategory(data));
	}

	@GetMapping
	public List<CategoryDTO> getAllCategories() {

		Iterable<Category> categories = getCategoryService().getAllCategories();
		return getCategoryMapper().mapToDTOList(categories);

	}
	// TODO: PUT and DELETE, GET one

}

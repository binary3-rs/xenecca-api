package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewCategoryDTO;
import com.xenecca.api.dto.response.CategoryDTO;
import com.xenecca.api.mapper.CategoryMapper;
import com.xenecca.api.model.course.Category;
import com.xenecca.api.service.CategoryService;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

	@Autowired
	private CategoryService _categoryService;

	@Autowired
	private CategoryMapper _categoryMapper;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@ApiOperation(value = "Add new category")
	public CategoryDTO addCategory(@RequestBody NewCategoryDTO categoryData) {
		return getCategoryMapper().mapToDTO(getCategoryService().addCategory(categoryData));
	}

	@GetMapping
	@ApiOperation(value = "Get all categories", produces = "application/json")
	public List<CategoryDTO> getAllCategories() {
		Iterable<Category> categories = getCategoryService().getAllCategories();
		return getCategoryMapper().mapToDTOList(categories);
	}

}

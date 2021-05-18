package com.xenecca.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewLearningResourceCategoryDTO;
import com.xenecca.api.dto.response.LearningResourceCategoryDTO;
import com.xenecca.api.mapper.LearningResourceCategoryMapper;
import com.xenecca.api.model.learnresource.LearningResourceCategory;
import com.xenecca.api.model.type.LearningResourceDomain;
import com.xenecca.api.service.impl.LearningResourceCategoryService;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@RestController
@RequestMapping("/api/v1/resource-categories/")
public class LearningResourceCategoryController {

	@Autowired
	private LearningResourceCategoryService _resourceCategoryService;

	@Autowired
	private LearningResourceCategoryMapper _resourceCategoryMapper;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@ApiOperation(value = "Add new learning resource category")
	public LearningResourceCategoryDTO addLearningResourceCategory(
			@Valid @ModelAttribute NewLearningResourceCategoryDTO resourceCategoryData) {
		return getResourceCategoryMapper()
				.mapToDTO(getResourceCategoryService().addResourceCategory(resourceCategoryData));
	}

	@GetMapping
	@ApiOperation(value = "Get learning resource categories (by domain)", notes = "Get resource categories. If the domain is ommited, returns all categories.")
	public List<LearningResourceCategoryDTO> getResourceCategories(LearningResourceDomain domain) {
		Iterable<LearningResourceCategory> categories = (domain == null)
				? getResourceCategoryService().getAllResourceCategories()
				: getResourceCategoryService().getResourceCategoriesByDomain(domain);
		return getResourceCategoryMapper().mapToDTOList(categories);
	}

	@GetMapping("/domains")
	@ApiOperation(value = "Get learning resource category domains")
	public Map<String, String> getResourceCategoryDomains() {
		Map<String, String> domains = new HashMap<String, String>();
		for (LearningResourceDomain domain : LearningResourceDomain.values()) {
			domains.put(domain.toString(), domain.getName());
		}
		return domains;
	}

	@PutMapping("{id}")
	@ApiOperation(value = "Update learning resource category")
	public LearningResourceCategoryDTO updateLearningResourceCategory(@PathVariable("id") Long resourceCategoryId,
			@Valid @ModelAttribute NewLearningResourceCategoryDTO resourceCategoryData) {
		return getResourceCategoryMapper().mapToDTO(
				getResourceCategoryService().updateResourceCategory(resourceCategoryId, resourceCategoryData));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	@ApiOperation(value = "Delete learning resource category")
	public void deleteLearningResourceCategory(@PathVariable("id") Long resourceCategoryId) {
		getResourceCategoryService().deleteResourceCategory(resourceCategoryId);
	}

}

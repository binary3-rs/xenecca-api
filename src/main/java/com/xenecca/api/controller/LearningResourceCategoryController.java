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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewLearningResourceCategoryDTO;
import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.dto.response.LearningResourceCategoryDTO;
import com.xenecca.api.dto.response.LearningResourceDTO;
import com.xenecca.api.mapper.LearningResourceCategoryMapper;
import com.xenecca.api.model.learnresource.LearningResource;
import com.xenecca.api.model.type.LearningResourceDomain;
import com.xenecca.api.model.type.ResourceType;
import com.xenecca.api.service.impl.LearningResourceCategoryService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@RestController
@RequestMapping("/api/v1/resources/categories")
public class LearningResourceCategoryController {

	@Autowired
	private LearningResourceCategoryService _resourceCategoryService;

	@Autowired
	private LearningResourceCategoryMapper _resourceCategoryMapper;

	@PostMapping
	public LearningResourceCategoryDTO addLearningResourceCategory(
			@Valid @RequestBody NewLearningResourceCategoryDTO resourceCategoryDTO) {
		return getResourceCategoryMapper()
				.mapToDTO(getResourceCategoryService().addResourceCategory(resourceCategoryDTO));
	}

	@GetMapping
	public List<LearningResourceCategoryDTO> getResourceCategories() {
		return getResourceCategoryMapper().mapToDTOList(getResourceCategoryService().getAllResourceCategories());
	}

	@GetMapping("/domains")
	public Map<String, String> getResourceCategoryDomains() {
		Map<String, String> domains = new HashMap<String, String>();
		for (LearningResourceDomain domain : LearningResourceDomain.values()) {
			domains.put(domain.toString(), domain.getName());
		}
		return domains;
	}

	@PutMapping("{id}")
	public LearningResourceCategoryDTO updateLearningResourceCategory(@PathVariable("id") Long resourceCategoryId,
			@Valid @RequestBody NewLearningResourceCategoryDTO resourceCategoryDTO) {
		return getResourceCategoryMapper()
				.mapToDTO(getResourceCategoryService().updateResourceCategory(resourceCategoryId, resourceCategoryDTO));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void deleteLearningResourceCategory(@PathVariable("id") Long resourceCategoryId) {
		getResourceCategoryService().deleteResourceCategory(resourceCategoryId);
	}

}

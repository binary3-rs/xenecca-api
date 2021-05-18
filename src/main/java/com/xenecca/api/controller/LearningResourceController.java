package com.xenecca.api.controller;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.dto.response.LearningResourceDTO;
import com.xenecca.api.dto.response.LearningResourceTypeDTO;
import com.xenecca.api.mapper.LearningResourceMapper;
import com.xenecca.api.model.learnresource.LearningResource;
import com.xenecca.api.model.type.ResourceType;
import com.xenecca.api.service.LearningResourceService;
import com.xenecca.api.service.SearchService;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@RestController
@RequestMapping("/api/v1/resources/")
public class LearningResourceController {

	@Autowired
	private LearningResourceService _learningResourceService;

	@Autowired
	private SearchService _searchService;

	@Autowired
	private LearningResourceMapper _learningResourceMapper;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@ApiOperation(value = "Add new file learning resource.")
	public LearningResourceDTO addLearningResource(@Valid @ModelAttribute NewLearningResourceDTO learningResourceData) {
		return getLearningResourceMapper().mapToDTO(getLearningResourceService().addLearningResource(learningResourceData));
	}

	@GetMapping
	@ApiOperation(value = "Get learning resources.")
	public List<LearningResourceDTO> getResources(@RequestParam(name = "q", required = false) String searchTerm,
			@RequestParam(name = "category", required = false) Long categoryId,
			@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo) {
		if (searchTerm == null && categoryId == null) {
			Iterable<LearningResource> resources = getLearningResourceService().getAllResources(pageNo);
			return getLearningResourceMapper().mapToDTOList(resources);
		}
		return getLearningResourceMapper()
				.mapDocsToDTOList(getSearchService().searchResources(searchTerm, categoryId, pageNo));
	}

	@GetMapping(value = "/{id}", produces = MediaType.ALL_VALUE)
	@ApiOperation(value = "Get the file learning resource.", produces = "application/octet-stream")
	public ResponseEntity<InputStreamResource> getFileResource(@PathVariable("id") Long resourceId) {
		Map<String, Object> fileData = getLearningResourceService().getFileResource(resourceId);
		ByteArrayInputStream file = (ByteArrayInputStream) fileData.get("file");
		String name = fileData.get("name").toString();
		String[] mimeType = fileData.get("mimeType").toString().split("/");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("inline; filename=%s", name));
		return ResponseEntity.ok().headers(headers).contentType(new MediaType(mimeType[0], mimeType[1]))
				.body(new InputStreamResource(file));

	}

	@GetMapping("/types")
	@ApiOperation(value = "Get learning resource types")
	public LearningResourceTypeDTO getResourceTypes() {
		Map<String, String> types = new HashMap<String, String>();
		for (ResourceType type : ResourceType.values()) {
			types.put(type.toString(), type.getName());
		}
		return new LearningResourceTypeDTO(types);
	}

	@PutMapping("{id}")
	@ApiOperation(value = "Update the learning resource")
	public LearningResourceDTO updateLearningResource(@PathVariable("id") Long resourceId,
			@Valid @ModelAttribute NewLearningResourceDTO learningResourceData) {
		return getLearningResourceMapper()
				.mapToDTO(getLearningResourceService().updateLearningResource(resourceId, learningResourceData));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	@ApiOperation(value = "Delete learning resource")
	public void deleteLearningResource(@PathVariable("id") Long resourceId) {
		getLearningResourceService().deleteLearningResource(resourceId);
	}

}

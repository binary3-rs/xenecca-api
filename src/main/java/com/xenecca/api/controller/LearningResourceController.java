package com.xenecca.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.dto.response.LearningResourceDTO;
import com.xenecca.api.mapper.LearningResourceMapper;
import com.xenecca.api.service.LearningResourceService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@RestController
@RequestMapping("/api/v1/resources/")
public class LearningResourceController {

	@Autowired
	private LearningResourceService _learningResourceService;

	@Autowired
	private LearningResourceMapper _learningResourceMapper;

	@PostMapping
	public LearningResourceDTO addLearningResource(@Valid @ModelAttribute NewLearningResourceDTO learningResource) {
		return getLearningResourceMapper().mapToDTO(getLearningResourceService().addLearningResource(learningResource));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void deleteLearningResource(@PathVariable("id") Long resourceId,
			@Valid @ModelAttribute NewLearningResourceDTO learningResource) {
		getLearningResourceService().deleteLearningResource(resourceId);
	}

	@PutMapping("{id}")
	public void updateLearningResource(@PathVariable("id") Long resourceId,
			@Valid @ModelAttribute NewLearningResourceDTO learningResource) {
		getLearningResourceService().updateLearningResource(resourceId, learningResource);
	}

}

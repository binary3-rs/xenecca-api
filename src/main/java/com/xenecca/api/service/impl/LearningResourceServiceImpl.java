package com.xenecca.api.service.impl;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.xenecca.api.dao.LearningResourceCategoryRepository;
import com.xenecca.api.dao.LearningResourceRepository;
import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.exception.FileStorageException;
import com.xenecca.api.exception.InvalidRequestDataException;
import com.xenecca.api.mapper.LearningResourceMapper;
import com.xenecca.api.model.learnresource.LearningResource;
import com.xenecca.api.model.type.MaterialType;
import com.xenecca.api.service.LearningResourceService;
import com.xenecca.api.utils.FileUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@Service
public class LearningResourceServiceImpl implements LearningResourceService {

	@Autowired
	private LearningResourceRepository _learningResourceRepository;
	@Autowired
	private LearningResourceCategoryRepository _learningCategoryRepository;

	@Autowired
	private LearningResourceMapper _learningResourceMaper;

	@Override
	public LearningResource addLearningResource(NewLearningResourceDTO learningResource) {
		MaterialType materialType = learningResource.getMaterialType();
		String resourceValue = getResourceValue(learningResource);
		LearningResource resource = LearningResource.builder().name(learningResource.getName())
				.materialType(learningResource.getMaterialType()).resource(resourceValue)
				.resourceType(learningResource.getResourceType())
				.resourceCategory(
						getLearningCategoryRepository().findById(learningResource.getResourceCategoryId()).get())
				.build();
		try {
			return getLearningResourceRepository().save(resource);
		} catch (Exception e) {
			if (materialType.equals(MaterialType.FILE)) {
				FileUtils.deleteFile(Paths.get(resourceValue));
			}
			throw e;

		}
	}

	@Override
	public LearningResource updateLearningResource(Long resourceId, NewLearningResourceDTO learningResource) {
		MaterialType materialType = learningResource.getMaterialType();

		String resourceValue = getResourceValue(learningResource);
		LearningResource resource = getLearningResourceRepository().findById(resourceId).get();
		String oldFile = null;
		if (resource.getMaterialType().equals(MaterialType.FILE)) {
			oldFile = resource.getResource();
		}
		resource.setMaterialType(materialType);
		resource.setName(learningResource.getName());
		resource.setResource(resourceValue);
		resource.setResourceCategory(
				getLearningCategoryRepository().findById(learningResource.getResourceCategoryId()).get());
		resource.setResourceType(learningResource.getResourceType());

		try {
			resource = getLearningResourceRepository().save(resource);
			FileUtils.deleteFile(Paths.get(oldFile));
			return  resource;
		} catch (Exception e) {
			if (materialType.equals(MaterialType.FILE)) {
				resource.setResource(oldFile);
				FileUtils.deleteFile(Paths.get(resourceValue));

			}
			throw e;

		}

	}

	@Override
	public void deleteLearningResource(Long resourceId) {
		LearningResource resource = getLearningResourceRepository().findById(resourceId).get();
		if (resource.getMaterialType().equals(MaterialType.FILE)) {
			FileUtils.deleteFile(Paths.get(resource.getResource()));
		}
		getLearningResourceRepository().delete(resource);

	}

	private String getResourceValue(NewLearningResourceDTO learningResource) {
		MaterialType materialType = learningResource.getMaterialType();
		String resourceValue = null;
		if (materialType.equals(MaterialType.FILE)) {
			if (learningResource.getFile() == null) {
				throw new InvalidRequestDataException("You must upload the file for this type of resource!");
			}
			resourceValue = FileUtils.storeFile(learningResource.getFile());

		} else {
			if (learningResource.getResourceURL() == null) {
				throw new InvalidRequestDataException("You must provide resource value!");
			}
			resourceValue = learningResource.getResourceURL();
		}
		return resourceValue;
	}
}

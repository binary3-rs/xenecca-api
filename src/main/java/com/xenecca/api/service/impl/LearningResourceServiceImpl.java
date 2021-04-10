package com.xenecca.api.service.impl;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.LearningResourceCategoryRepository;
import com.xenecca.api.dao.LearningResourceRepository;
import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.exception.BadAPIRequestException;
import com.xenecca.api.exception.InvalidRequestDataException;
import com.xenecca.api.model.learnresource.LearningResource;
import com.xenecca.api.model.type.MaterialType;
import com.xenecca.api.service.LearningResourceService;
import com.xenecca.api.service.SearchService;
import com.xenecca.api.utils.FileUtils;
import com.xenecca.api.utils.SortAndCompareUtils;

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
	private SearchService _searchService;

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
			resource = getLearningResourceRepository().save(resource);
			getSearchService().storeResourceDocument(resource);
			return resource;
		} catch (Exception e) {
			if (materialType.equals(MaterialType.FILE)) {
				FileUtils.deleteFile(Paths.get(resourceValue));
			}
			throw e;
		}
	}

	@Override
	public Iterable<LearningResource> getAllResources(Integer pageNo) {
		Pageable sortedPageable = SortAndCompareUtils.createPageable(pageNo, null, null);
		Page<LearningResource> pageOfResources = getLearningResourceRepository().findAll(sortedPageable);
		return pageOfResources.getContent();
	}

	@Override
	public Map<String, Object> getFileResource(Long resourceId) {
		LearningResource resource = getLearningResourceRepository().findById(resourceId).get();
		if (!resource.getMaterialType().equals(MaterialType.FILE)) {
			throw new BadAPIRequestException("You cannot fetch file content for this resource(URL type)!");
		}
		ByteArrayInputStream file = FileUtils.getFile(Paths.get(resource.getResource()));
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		String mimeType = mimeTypesMap.getContentType(resource.getResource());
		String fileExtension = fileExtension(resource.getResource());
		Map<String, Object> fileData = Map.of("file", file, "name", resource.getName() + "." + fileExtension,
				"mimeType", mimeType);
		return fileData;
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
			getSearchService().storeResourceDocument(resource);
			return resource;
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
		getSearchService().deleteResourceDocument(resourceId);

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

	private String fileExtension(String fileName) {
		String[] nameComponents = fileName.split("/");
		String cleanedName = nameComponents[nameComponents.length - 1];
		nameComponents = cleanedName.split("\\.");
		return (nameComponents.length == 2) ? nameComponents[1]
				: MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(fileName);

	}
}

package com.xenecca.api.service;

import java.util.Map;

import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.model.learnresource.LearningResource;
import com.xenecca.api.utils.model.PageResult;

public interface LearningResourceService {

	LearningResource addLearningResource(NewLearningResourceDTO learningResource);

	PageResult<LearningResource> getAllResources(Integer pageNo, Integer pageSize);

	Iterable<LearningResource> getAllResourcesByCategory(Long categoryId, Integer pageNo, Integer pageSize);

	Map<String, Object> getFileResource(Long resourceId);

	LearningResource updateLearningResource(Long resourceId, NewLearningResourceDTO learningResource);

	void deleteLearningResource(Long resourceId);

}

package com.xenecca.api.service;

import java.util.Map;

import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.model.learnresource.LearningResource;
import com.xenecca.api.utils.model.PageResult;

public interface LearningResourceService {

	public LearningResource addLearningResource(NewLearningResourceDTO learningResource);

	public PageResult<LearningResource> getAllResources(Integer pageNo, Integer pageSize);

	public Iterable<LearningResource> getAllResourcesByCategory(Long categoryId, Integer pageNo, Integer pageSize);

	public Map<String, Object> getFileResource(Long resourceId);

	public LearningResource updateLearningResource(Long resourceId, NewLearningResourceDTO learningResource);

	public void deleteLearningResource(Long resourceId);

}

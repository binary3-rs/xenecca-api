package com.xenecca.api.service;

import java.util.Map;

import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.model.learnresource.LearningResource;

public interface LearningResourceService {

	public LearningResource addLearningResource(NewLearningResourceDTO learningResource);

	public Iterable<LearningResource> getAllResources(Integer pageNo);
	
	public Iterable<LearningResource> getAllResourcesByCategory(Long categoryId, Integer pageNo);

	public Map<String, Object> getFileResource(Long resourceId);

	public LearningResource updateLearningResource(Long resourceId, NewLearningResourceDTO learningResource);

	public void deleteLearningResource(Long resourceId);

}

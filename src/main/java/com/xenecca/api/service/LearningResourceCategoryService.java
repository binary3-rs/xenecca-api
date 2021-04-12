package com.xenecca.api.service;

import java.util.Map;

import com.xenecca.api.dto.request.NewLearningResourceCategoryDTO;
import com.xenecca.api.model.learnresource.LearningResourceCategory;
import com.xenecca.api.model.type.LearningResourceDomain;

public interface LearningResourceCategoryService {

	public LearningResourceCategory addResourceCategory(NewLearningResourceCategoryDTO resourceCategory);

	public Iterable<LearningResourceCategory> getAllResourceCategories();
	
	public Iterable<LearningResourceCategory> getResourceCategoriesByDomain(LearningResourceDomain domain);

	public Map<String, String> getResourceCategoryDomains();

	public LearningResourceCategory updateResourceCategory(Long resourceCategoryId,
			NewLearningResourceCategoryDTO resourceCategory);

	public void deleteResourceCategory(Long resourceCategoryId);

}

package com.xenecca.api.service;

import com.xenecca.api.dto.request.NewLearningResourceCategoryDTO;
import com.xenecca.api.dto.request.NewLearningResourceDTO;
import com.xenecca.api.model.learnresource.LearningResourceCategory;

public interface LearningResourceCategoryService {

	public LearningResourceCategory addResourceCategory(NewLearningResourceCategoryDTO resourceCategory);

	public Iterable<LearningResourceCategory> getAllResourceCategories();

	public LearningResourceCategory updateResourceCategory(Long resourceCategoryId,
			NewLearningResourceCategoryDTO resourceCategory);

	public void deleteResourceCategory(Long resourceCategoryId);

}

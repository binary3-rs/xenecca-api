package com.xenecca.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.LearningResourceCategoryRepository;
import com.xenecca.api.dto.request.NewLearningResourceCategoryDTO;
import com.xenecca.api.mapper.LearningResourceCategoryMapper;
import com.xenecca.api.model.learnresource.LearningResourceCategory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@Service
public class LearningResourceCategoryService implements com.xenecca.api.service.LearningResourceCategoryService {

	@Autowired
	private LearningResourceCategoryRepository _resourceCategoryRepository;

	@Autowired
	private LearningResourceCategoryMapper _resourceCategoryMapper;

	@Override
	public LearningResourceCategory addResourceCategory(NewLearningResourceCategoryDTO resourceCategory) {
		LearningResourceCategory category = getResourceCategoryMapper().mapToEntity(resourceCategory);
		return getResourceCategoryRepository().save(category);
	}

	@Override
	public Iterable<LearningResourceCategory> getAllResourceCategories() {
		return getResourceCategoryRepository().findAll();

	}

	@Override
	public LearningResourceCategory updateResourceCategory(Long resourceCategoryId,
			NewLearningResourceCategoryDTO resourceCategory) {
		LearningResourceCategory category = getResourceCategoryRepository().findById(resourceCategoryId).get();
		category.setName(resourceCategory.getName());
		category.setDomain(resourceCategory.getDomain());
		return getResourceCategoryRepository().save(category);
	}

	@Override
	public void deleteResourceCategory(Long resourceCategoryId) {
		getResourceCategoryRepository().deleteById(resourceCategoryId);

	}

}

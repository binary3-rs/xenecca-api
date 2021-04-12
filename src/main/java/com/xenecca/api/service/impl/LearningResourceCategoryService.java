package com.xenecca.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.LearningResourceCategoryRepository;
import com.xenecca.api.dto.request.NewLearningResourceCategoryDTO;
import com.xenecca.api.mapper.LearningResourceCategoryMapper;
import com.xenecca.api.model.learnresource.LearningResourceCategory;
import com.xenecca.api.model.type.LearningResourceDomain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
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
	@Cacheable(cacheNames = "resource-categories")
	public Iterable<LearningResourceCategory> getAllResourceCategories() {
		return getResourceCategoryRepository().findAll();

	}

	@Override
	@Cacheable(cacheNames = "resource-categories")
	public Iterable<LearningResourceCategory> getResourceCategoriesByDomain(LearningResourceDomain domain) {
		return getResourceCategoryRepository().findBy_domain(domain);
	}

	@Override
	@Cacheable(cacheNames = "resource-domains")
	public Map<String, String> getResourceCategoryDomains() {
		Map<String, String> domains = new HashMap<String, String>();
		for (LearningResourceDomain domain : LearningResourceDomain.values()) {
			domains.put(domain.toString(), domain.getName());
		}
		return domains;
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

package com.xenecca.api.service.impl;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xenecca.api.dao.LearningResourceCategoryRepository;
import com.xenecca.api.dto.request.NewLearningResourceCategoryDTO;
import com.xenecca.api.mapper.LearningResourceCategoryMapper;
import com.xenecca.api.model.learnresource.LearningResourceCategory;
import com.xenecca.api.model.type.LearningResourceDomain;
import com.xenecca.api.utils.FileUtils;
import com.xenecca.api.utils.FileUtils.StorageType;

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
		MultipartFile logoFile = resourceCategory.getLogo();
		String logo = null;
		if (logoFile != null) {
			logo = FileUtils.storeFile(resourceCategory.getLogo(), StorageType.RESOURCE_CATEGORY);
		}

		LearningResourceCategory category = LearningResourceCategory.builder().name(resourceCategory.getName())
				.tags(resourceCategory.getTags()).domain(resourceCategory.getDomain()).logo(logo).build();
		return getResourceCategoryRepository().save(category);
	}

	@Override
	@Cacheable(cacheNames = "resource-categories")
	public Iterable<LearningResourceCategory> getAllResourceCategories() {
		return getResourceCategoryRepository().findAll();

	}

	@Override
	//@Cacheable(cacheNames = "resource-categories")
	public Iterable<LearningResourceCategory> getResourceCategoriesByDomain(LearningResourceDomain domain) {
		return getResourceCategoryRepository().findBy_domain(domain);
	}

	@Override
	//@Cacheable(cacheNames = "resource-domains")
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
		category.setTags(resourceCategory.getTags());
		String logo = category.getLogo();

		if (resourceCategory.getLogo() != null) {
			logo = FileUtils.storeFile(resourceCategory.getLogo(), StorageType.RESOURCE_CATEGORY);
			if (category.getLogo() != null) {
				FileUtils.deleteFile(Paths.get(category.getLogo()));
			}
		}
		category.setLogo(logo);
		return getResourceCategoryRepository().save(category);
	}

	@Override
	public void deleteResourceCategory(Long resourceCategoryId) {
		LearningResourceCategory category = getResourceCategoryRepository().findById(resourceCategoryId).get();
		String logoPath = category.getLogo();
		getResourceCategoryRepository().delete(category);
		FileUtils.deleteFile(Paths.get(logoPath));
	}

}

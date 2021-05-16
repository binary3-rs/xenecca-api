package com.xenecca.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.learnresource.LearningResourceCategory;
import com.xenecca.api.model.type.LearningResourceDomain;

@Repository
public interface LearningResourceCategoryRepository extends CrudRepository<LearningResourceCategory, Long> {
	public Iterable<LearningResourceCategory> findBy_domain(LearningResourceDomain domain);
	public LearningResourceCategory findBy_name(String name);
}

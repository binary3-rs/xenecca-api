package com.xenecca.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.learnresource.LearningResourceCategory;
@Repository
public interface LearningResourceCategoryRepository extends CrudRepository<LearningResourceCategory, Long> {

}

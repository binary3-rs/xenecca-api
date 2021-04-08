package com.xenecca.api.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.xenecca.api.model.learnresource.LearningResource;

@Repository
public interface LearningResourceRepository extends PagingAndSortingRepository<LearningResource, Long> {
}

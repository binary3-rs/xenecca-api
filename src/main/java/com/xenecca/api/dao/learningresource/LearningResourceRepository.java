package com.xenecca.api.dao.learningresource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.learnresource.LearningResource;

@Repository
public interface LearningResourceRepository extends PagingAndSortingRepository<LearningResource, Long> {
	Page<LearningResource> findBy_resourceCategory__id(Long categoryId, Pageable pageable);

}

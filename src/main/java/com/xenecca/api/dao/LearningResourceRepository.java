package com.xenecca.api.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.xenecca.api.model.learnresource.LearningResource;

@Repository
public interface LearningResourceRepository extends PagingAndSortingRepository<LearningResource, Long> {
	List<LearningResource> findAllBy_resourceCategory__id(Long id, Pageable pageable);
}

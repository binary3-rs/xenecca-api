package com.xenecca.api.dao;

import com.xenecca.api.model.studentopportunity.StudentOpportunity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOpportunityRepository extends PagingAndSortingRepository<StudentOpportunity, String> {

    int deleteBy_id(String studentOpportunityId);
}

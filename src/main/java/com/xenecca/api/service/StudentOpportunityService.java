package com.xenecca.api.service;

import com.xenecca.api.dto.request.StudentOpportunityReqDto;
import com.xenecca.api.dto.response.StudentOpportunityRespDto;

import java.util.List;
import java.util.Map;

public interface StudentOpportunityService {
    StudentOpportunityRespDto createStudentOpportunity(final StudentOpportunityReqDto studentOpportunityReq);

    Map<String, List<StudentOpportunityRespDto>> getStudentOpportunities();

    StudentOpportunityRespDto updateStudentOpportunity(final String studentOpportunityId, final StudentOpportunityReqDto studentOpportunityReq);

    void deleteStudentOpportunity(final String studentOpportunityId);
}

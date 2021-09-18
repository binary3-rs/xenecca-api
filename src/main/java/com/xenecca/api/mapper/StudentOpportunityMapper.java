package com.xenecca.api.mapper;

import com.xenecca.api.dto.request.StudentOpportunityReqDto;
import com.xenecca.api.dto.response.StudentOpportunityRespDto;
import com.xenecca.api.model.studentopportunity.StudentOpportunity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentOpportunityMapper {

    StudentOpportunity mapToEntity(StudentOpportunityReqDto studentOpportunityReqDto);

    StudentOpportunityRespDto mapToDto(StudentOpportunity studentOpportunity);

    List<StudentOpportunityRespDto> mapToDtoList(List<StudentOpportunity> studentOpportunities);
}

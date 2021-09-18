package com.xenecca.api.service.impl;

import com.xenecca.api.dao.StudentOpportunityRepository;
import com.xenecca.api.dto.request.StudentOpportunityReqDto;
import com.xenecca.api.dto.response.StudentOpportunityRespDto;
import com.xenecca.api.exception.ResourceNotFoundException;
import com.xenecca.api.mapper.StudentOpportunityMapper;
import com.xenecca.api.model.studentopportunity.StudentOpportunity;
import com.xenecca.api.service.StudentOpportunityService;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Accessors(prefix = "_")
@Slf4j
@Getter
@Service
@Transactional
public class StudentOpportunityServiceImpl implements StudentOpportunityService {

    @Autowired
    private StudentOpportunityRepository _studentOpportunityRepository;

    @Autowired
    private StudentOpportunityMapper _studentOpportunityMapper;

    @Override
    public StudentOpportunityRespDto createStudentOpportunity(StudentOpportunityReqDto studentOpportunityReq) {
        log.info("Creating new student opportunity from request data = [{}]", studentOpportunityReq);
        StudentOpportunity studentOpportunity = getStudentOpportunityMapper().mapToEntity(studentOpportunityReq);
        studentOpportunity = getStudentOpportunityRepository().save(studentOpportunity);
        log.info("Student opportunity [{}] created", studentOpportunity);
        return getStudentOpportunityMapper().mapToDto(studentOpportunity);
    }

    // TODO: caching
    @Override
    public Map<String, List<StudentOpportunityRespDto>> getStudentOpportunities() {
        log.info("Fetching student opportunities...");
        List<StudentOpportunity> studentOpportunities = (List<StudentOpportunity>) getStudentOpportunityRepository().findAll();
        log.info("Student opportunities fetched");
        return studentOpportunities
                .stream()
                .map(studentopp -> getStudentOpportunityMapper().mapToDto(studentopp))
                .collect(Collectors.groupingBy(studentopDto -> studentopDto.getOpportunityType().toString()));
    }

    @Override
    public StudentOpportunityRespDto updateStudentOpportunity(String studentOpportunityId, StudentOpportunityReqDto studentOpportunityReq) {
        log.info("Updating a student opportunity with id={} with request data = [{}]", studentOpportunityId, studentOpportunityReq);
        getStudentOpportunityRepository().findById(studentOpportunityId).orElseThrow(() -> new ResourceNotFoundException("Student opportunity not found!"));
        StudentOpportunity updatedStudentOpportunity = getStudentOpportunityMapper().mapToEntity(studentOpportunityReq);
        updatedStudentOpportunity.setId(studentOpportunityId);
        updatedStudentOpportunity = getStudentOpportunityRepository().save(updatedStudentOpportunity);
        log.info("Student opportunity with id={} updated", studentOpportunityId);
        return getStudentOpportunityMapper().mapToDto(updatedStudentOpportunity);
    }

    @Override
    public void deleteStudentOpportunity(String studentOpportunityId) {
        log.info("Deleting a student opportunity with id={}", studentOpportunityId);
        int numOfDeletedRecords = getStudentOpportunityRepository().deleteBy_id(studentOpportunityId);
        if (numOfDeletedRecords == 0) {
            throw new ResourceNotFoundException("Student opportunity not found!");
        }
        log.info("Student opportunity with id={} deleted", studentOpportunityId);
    }
}

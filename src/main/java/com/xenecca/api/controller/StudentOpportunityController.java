package com.xenecca.api.controller;

import com.xenecca.api.dto.request.StudentOpportunityReqDto;
import com.xenecca.api.dto.response.StudentOpportunityRespDto;
import com.xenecca.api.service.StudentOpportunityService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Getter(AccessLevel.PRIVATE)
@Slf4j
@RestController
@RequestMapping("/v1/student-opportunities/")
public class StudentOpportunityController {

    @Autowired
    private StudentOpportunityService studentOpportunityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentOpportunityRespDto createStudentOpportunity(@Valid @RequestBody StudentOpportunityReqDto studentOpportunityReq) {
        log.info("Received request for creating new student opportunity");
        return getStudentOpportunityService().createStudentOpportunity(studentOpportunityReq);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<StudentOpportunityRespDto>> getAllStudentOpportunities() {
        log.info("Received request for fetching all student opportunity");
        return getStudentOpportunityService().getStudentOpportunities();
    }


    @PutMapping("/{studentOpportunityId}")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentOpportunityRespDto updateStudentOpportunity(@PathVariable("studentOpportunityId") String studentOpportunityId,
                                                              @Valid @RequestBody StudentOpportunityReqDto studentOpportunityReq) {
        log.info("Received request for updating a student opportunity");
        return getStudentOpportunityService().updateStudentOpportunity(studentOpportunityId, studentOpportunityReq);
    }

    @DeleteMapping("/{studentOpportunityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentOpportunity(@PathVariable("studentOpportunityId") String studentOpportunityId) {
        log.info("Received request for deleting a student opportunity");
        getStudentOpportunityService().deleteStudentOpportunity(studentOpportunityId);
    }


    // TODO: - better validation messages for req dto
}

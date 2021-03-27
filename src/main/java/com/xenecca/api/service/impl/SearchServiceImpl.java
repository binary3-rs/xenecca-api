package com.xenecca.api.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.es.CourseDocRepository;
import com.xenecca.api.es.models.CourseDoc;
import com.xenecca.api.es.models.InstructorDoc;
import com.xenecca.api.service.SearchService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private CourseDocRepository _courseDocRepository;

	@Override
	public boolean addDocument() {
		CourseDoc doc = new CourseDoc();
		doc.setDocId(1L);
		doc.setTitle("Test");
		doc.setAvgRating(2.3);
		doc.setCategory(2L);
		doc.setHeadline("Test headline");
		doc.setLanguage(1L);
		doc.setPrice(200.0);
		doc.setSubcategory(1L);
		doc.setTopic(1L);
		doc.setInstructors(Arrays.asList(new InstructorDoc("John Smith", null), new InstructorDoc("John Doe", null)));
		System.out.println(getCourseDocRepository().save(doc));
		return false;
	}

}

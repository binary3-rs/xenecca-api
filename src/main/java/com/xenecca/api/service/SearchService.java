package com.xenecca.api.service;

import java.util.List;

import com.xenecca.api.es.models.CourseDoc;

public interface SearchService {

	public boolean addDocument();

	public List<CourseDoc> searchCourses(String searchTerm, Integer categoryId, Integer subcategoryId, Integer topicId,
			Integer languageId, Float rating, List<String> duration, Integer pageNo, String sortBy, String order);

	public void deleteCourseById(Long courseId);
}

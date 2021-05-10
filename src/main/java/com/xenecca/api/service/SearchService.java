package com.xenecca.api.service;

import java.util.List;

import com.xenecca.api.es.models.CourseDoc;
import com.xenecca.api.es.models.LearningResourceDoc;
import com.xenecca.api.model.learnresource.LearningResource;

public interface SearchService {

	public List<CourseDoc> searchCourses(String searchTerm, Integer categoryId, Integer subcategoryId,
			Integer languageId, Integer pageNo, String sortBy, String order);

	public void deleteCourseDocument(Long courseId);

	public void storeResourceDocument(LearningResource resource);

	public void deleteResourceDocument(Long resourceId);

	public List<LearningResourceDoc> searchResources(String searchTerm, Long categoryId, Integer pageNo);

}

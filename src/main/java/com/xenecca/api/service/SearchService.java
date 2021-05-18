package com.xenecca.api.service;

import com.xenecca.api.model.elastic.CourseDoc;
import com.xenecca.api.model.elastic.LearningResourceDoc;
import com.xenecca.api.model.learnresource.LearningResource;
import com.xenecca.api.model.type.MaterialType;
import com.xenecca.api.model.type.ResourceType;
import com.xenecca.api.utils.model.PageResult;

public interface SearchService {

	public void deleteCourseDocument(Long courseId);

	public void deleteResourceDocument(Long resourceId);

	public PageResult<CourseDoc> searchCourses(String searchTerm, Integer categoryId, Integer subcategoryId,
			Integer languageId, Integer pageNo, Integer pageSize);

	public PageResult<LearningResourceDoc> searchResources(String searchTerm, Long categoryId,
			ResourceType resourceType, MaterialType materialType, Integer pageNo, Integer pageSize);

	public void storeResourceDocument(LearningResource resource);

}

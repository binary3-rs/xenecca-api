package com.xenecca.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.es.CourseDocRepository;
import com.xenecca.api.dao.es.LearningResourceDocRepository;
import com.xenecca.api.es.models.CourseDoc;
import com.xenecca.api.es.models.LearningResourceDoc;
import com.xenecca.api.model.learnresource.LearningResource;
import com.xenecca.api.service.SearchService;
import com.xenecca.api.utils.SortAndCompareUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private CourseDocRepository _courseDocRepository;

	@Autowired
	private LearningResourceDocRepository _resourceDocRepository;

	@Autowired
	private ElasticsearchOperations _template;

	@Override
	public List<CourseDoc> searchCourses(String searchTerm, Integer categoryId, Integer subcategoryId,
			Integer languageId, Integer pageNo, String sortBy, String order) {
		List<CourseDoc> courseList = new ArrayList<CourseDoc>();
		Criteria criteria = createCourseCriteriaBasedOnParams(searchTerm, categoryId, subcategoryId, languageId);
		Query query = new CriteriaQuery(criteria);
		Pageable pageable = SortAndCompareUtils.createPageable(pageNo, sortBy, order);
		query.setPageable(pageable);
		SearchHits<CourseDoc> courses = getTemplate().search(query, CourseDoc.class);
		for (SearchHit<CourseDoc> courseHit : courses.getSearchHits()) {
			CourseDoc courseDoc = courseHit.getContent();
			if (courseDoc != null) {
				courseList.add(courseDoc);
			}
		}
		return courseList;
	}

	@Override
	public void deleteCourseDocument(Long courseId) {
		getCourseDocRepository().deleteById(courseId);
	}

	@Override
	public void storeResourceDocument(LearningResource resource) {
		LearningResourceDoc doc = LearningResourceDoc.builder().docId(resource.getId())
				.category(resource.getResourceCategory().getId()).name(resource.getName())
				.materialType(resource.getMaterialType().toString()).resource(resource.getResource())
				.resourceType(resource.getResourceType().getName()).build();
		getResourceDocRepository().save(doc);
	}

	@Override
	public void deleteResourceDocument(Long resourceId) {
		getResourceDocRepository().deleteById(resourceId);
	}

	@Override
	public List<LearningResourceDoc> searchResources(String searchTerm, Long categoryId, Integer pageNo) {
		List<LearningResourceDoc> resourceList = new ArrayList<LearningResourceDoc>();
		Criteria criteria = createResourceCriteriaBasedOnParams(searchTerm, categoryId);
		Query query = new CriteriaQuery(criteria);
		Pageable pageable = SortAndCompareUtils.createPageable(pageNo, null, null);
		query.setPageable(pageable);
		SearchHits<LearningResourceDoc> resources = getTemplate().search(query, LearningResourceDoc.class);
		for (SearchHit<LearningResourceDoc> resourceHit : resources.getSearchHits()) {
			LearningResourceDoc resourceDoc = resourceHit.getContent();
			if (resourceDoc != null) {
				resourceList.add(resourceDoc);
			}

		}
		return resourceList;
	}

	private Criteria createCourseCriteriaBasedOnParams(String searchTerm, Integer categoryId, Integer subcategoryId,
			Integer languageId) {
		Criteria criteria = new Criteria();
		if (searchTerm != null && !searchTerm.isEmpty()) {
			criteria.subCriteria(new Criteria("title").contains(searchTerm).or("headline").contains(searchTerm));
		}

		if (categoryId != null || subcategoryId != null) {
			if (subcategoryId != null) {
				criteria.subCriteria(new Criteria("subcategory").matches(subcategoryId));
			} else {
				criteria.subCriteria(new Criteria("category").matches(categoryId));
			}
		}

		if (languageId != null) {
			criteria.subCriteria(new Criteria("language").matches(languageId));
		}

		return criteria;
	}

	private Criteria createResourceCriteriaBasedOnParams(String searchTerm, Long categoryId) {
		Criteria criteria = new Criteria();
		if (searchTerm != null && !searchTerm.isEmpty()) {
			criteria.subCriteria(new Criteria("name").contains(searchTerm));
		}

		if (categoryId != null) {
			criteria.subCriteria(new Criteria("category").matches(categoryId));
		}

		return criteria;

	}

}

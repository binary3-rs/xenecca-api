package com.xenecca.api.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import com.xenecca.api.es.models.CourseDoc;
import com.xenecca.api.es.models.InstructorDoc;
import com.xenecca.api.service.SearchService;
import com.xenecca.api.utils.Constants;
import com.xenecca.api.utils.SortAndCompareUtils;

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

	@Autowired
	private ElasticsearchOperations _template;

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

	@Override
	public List<CourseDoc> searchCourses(String searchTerm, Integer categoryId, Integer subcategoryId, Integer topicId,
			Integer languageId, Float rating, List<String> duration, Integer pageNo, String sortBy, String order) {
		List<CourseDoc> resp = new ArrayList<CourseDoc>();
		Criteria criteria = createCriteriaBasedOnParams(searchTerm, categoryId, subcategoryId, topicId, languageId,
				rating, duration);
		Query query = new CriteriaQuery(criteria);
		Pageable pageable = SortAndCompareUtils.createPageable(pageNo, sortBy, order);
		query.setPageable(pageable);
		SearchHits<CourseDoc> courses = getTemplate().search(query, CourseDoc.class);
		for (SearchHit<CourseDoc> course : courses.getSearchHits()) {
			CourseDoc res = course.getContent();
			if (res != null) {
				resp.add(course.getContent());
			}

		}
		return resp;
	}

	private Criteria createCriteriaBasedOnParams(String searchTerm, Integer categoryId, Integer subcategoryId,
			Integer topicId, Integer languageId, Float rating, List<String> duration) {
		Criteria criteria = new Criteria();
		if (searchTerm != null && !searchTerm.isEmpty()) {
			criteria.subCriteria(new Criteria("title").contains(searchTerm).or("headline").contains(searchTerm));
		}

		if (categoryId != null || subcategoryId != null || topicId != null) {
			if (topicId != null) {
				criteria.subCriteria(new Criteria("topic").matches(topicId));
			} else if (subcategoryId != null) {
				criteria.subCriteria(new Criteria("subcategory").matches(subcategoryId));

			} else {
				criteria.subCriteria(new Criteria("category").matches(categoryId));
			}
		}

		if (languageId != null) {
			criteria.subCriteria(new Criteria("language").matches(languageId));
		}
		if (rating != null) {
			criteria.subCriteria(new Criteria("avg_rating").greaterThanEqual(rating));
		}
		if (duration != null) {
			criteria.subCriteria(composeDurationCriteria(createDurationCriteria(duration)));
		}

		return criteria;
	}

	private List<Criteria> createDurationCriteria(List<String> duration) {

		List<Criteria> durationCriteria = new ArrayList<Criteria>();
		Map<String, Map<String, Integer>> limits = Constants.durationLimits;

		for (String limit : duration) {
			if (limits.containsKey(limit)) {
				int lowerLimit = limits.get(limit).get("lower");
				int upperLimit = limits.get(limit).get("upper");
				durationCriteria.add(new Criteria("duration_in_hrs").between(lowerLimit, upperLimit));
			}

		}

		return durationCriteria;
	}

	private Criteria composeDurationCriteria(List<Criteria> criteria) {
		switch (criteria.size()) {
		case 0:
			return null;
		case 1:
			return criteria.get(0);
		case 2:
			return criteria.get(0).or(criteria.get(1));
		case 3:
			return criteria.get(0).or(criteria.get(1)).or(criteria.get(2));
		case 4:
			return criteria.get(0).or(criteria.get(1)).or(criteria.get(2)).or(criteria.get(3));
		default:
			return null;
		}

	}

}

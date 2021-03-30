package com.xenecca.api.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
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
			Integer languageId, Boolean paid) {
		List<CourseDoc> resp = new ArrayList<CourseDoc>();
		// Criteria criteria = new
		// Criteria("title").contains(searchTerm).or("headline").contains(searchTerm);
		Criteria criteria = createCriteriaBasedOnParams(searchTerm, categoryId, subcategoryId, topicId, languageId,
				paid);
		Query query = new CriteriaQuery(criteria);
		SearchHits<CourseDoc> courses = getTemplate().search(query, CourseDoc.class);
		// Iterator<SearchHit> hits
		for (SearchHit<CourseDoc> course : courses.getSearchHits()) {
			CourseDoc res = course.getContent();
			if (res != null) {
				resp.add(course.getContent());
			}

		}
		return resp;
		// if cate
//		
//		Pageable sortedByDateAddedDesc = PageRequest.of(0, 18, Sort.by("_createdAt").descending());
//		Page<CourseDoc> pageOfCourses = getCourseDocRepository().find(searchTerm, categoryId, subcategoryId, topicId,
//				languageId, paid, sortedByDateAddedDesc);

		// return pageOfCourses.getContent();
	}

	private Criteria createCriteriaBasedOnParams(String searchTerm, Integer categoryId, Integer subcategoryId,
			Integer topicId, Integer languageId, Boolean isPriceFree) {
		Criteria criteria = new Criteria();
		if (!searchTerm.isEmpty() && searchTerm != null) {
			criteria = criteria.and("title").contains(searchTerm).or("headline").contains(searchTerm);
		}
		if (categoryId != null || subcategoryId != null || topicId != null) {
			if (topicId != null) {
				criteria = criteria.and("topic").matches(topicId);
			} else if (subcategoryId != null) {
				criteria = criteria.and("subcategory").matches(subcategoryId);
			} else {
				criteria = criteria.and("category").matches(categoryId);
			}
		}

		if (languageId != null) {
			criteria = criteria.and("language").matches(languageId);
		}
		// TODO: check this out
		if (isPriceFree != null && isPriceFree == true) {
			System.out.println("DEBUG");
			criteria = criteria.and("price").matches(0.0);
		}

		return criteria;
	}
}

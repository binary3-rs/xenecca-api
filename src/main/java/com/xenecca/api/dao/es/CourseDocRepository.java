package com.xenecca.api.dao.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.xenecca.api.es.models.CourseDoc;

public interface CourseDocRepository extends ElasticsearchRepository<CourseDoc, Long> {
	// @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"firstName\",
	// \"lastName\"], \"fuzziness\": \"AUTO\"}}")
	// @Query("{\"bool\" : {\"should\" : [ {"field" : {"userId" : "?0"}}, {"field" :
	// {"questionId" : "?1"}} ]}}"")

//	@Query("{ \"bool\" : { \"should\" : [ { \"query_string\" : { \"query\" : \"*?0*\", \"fields\" : [ \"title\", \"headline\" ] }, \"analyze_wildcard\": true, \"fuzziness\": \"AUTO\" } ] } }")
//	Page<CourseDoc> find(String searchTerm, Integer categoryId, Integer subcategoryId, Integer topicId,
//			Integer languageId, Boolean paid, Pageable pageable);
	
	
	//@Query("{ \"bool\" : { \"should\" : [ { \"query_string\" : { \"query\" : \"*?0*\", \"fields\" : [ \"title\", \"headline\" ] }, \"analyze_wildcard\": true, \"fuzziness\": \"AUTO\" } ] } }")
	//Page<CourseDoc> findBy_titleOr_headlineContains(String searchTerm, Pageable pageable);
//	Integer categoryId, Integer subcategoryId, Integer topicId,
//			Integer languageId, Boolean paid, Pageable pageable);
}

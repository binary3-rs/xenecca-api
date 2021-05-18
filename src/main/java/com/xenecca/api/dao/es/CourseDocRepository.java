package com.xenecca.api.dao.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.elastic.CourseDoc;

@Repository
public interface CourseDocRepository extends ElasticsearchRepository<CourseDoc, Long> {

}

package com.xenecca.api.dao.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.xenecca.api.es.models.CourseDoc;

public interface CourseDocRepository extends ElasticsearchRepository<CourseDoc, Long>  {

}

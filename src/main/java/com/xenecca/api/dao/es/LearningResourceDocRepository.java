package com.xenecca.api.dao.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.elastic.LearningResourceDoc;

@Repository
public interface LearningResourceDocRepository extends ElasticsearchRepository<LearningResourceDoc, Long> {

}

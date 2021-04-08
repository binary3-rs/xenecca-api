package com.xenecca.api.dao.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.xenecca.api.es.models.LearningResourceDoc;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningResourceDocRepository extends ElasticsearchRepository<LearningResourceDoc, Long> {

}

package com.xenecca.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.TopicRepository;
import com.xenecca.api.model.Topic;
import com.xenecca.api.service.TopicService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository _topicRepository;

	@Override
	@Cacheable(cacheNames = "topics")
	public Iterable<Topic> getAllTopics() {
		return getTopicRepository().findAll();
	}

	@Override
	@Cacheable(cacheNames = "topics")
	public Iterable<Topic> getTopicsBySubcategoryId(Long subcategoryId) {
		return getTopicRepository().findBy_subcategory__id(subcategoryId);
	}

}

package com.xenecca.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.TopicRepository;
import com.xenecca.api.model.Topic;
import com.xenecca.api.service.TopicService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Accessors(prefix = "_")
@Getter
@Setter
@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository _topicRepository;

	@Override
	public Iterable<Topic> getAllTopics() {
		return getTopicRepository().findAll();
	}

	@Override
	public Iterable<Topic> getTopicsBySubcategoryId(Long subcategoryId) {
		return getTopicRepository().findBy_subcategory__id(subcategoryId);
	}

}

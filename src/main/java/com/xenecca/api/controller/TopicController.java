package com.xenecca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.response.TopicDTO;
import com.xenecca.api.mapper.TopicMapper;
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
@RestController
@RequestMapping("/api/v1/topics/")
public class TopicController {

	@Autowired
	private TopicService _topicService;

	@Autowired
	private TopicMapper _topicMapper;

	@GetMapping
	public List<TopicDTO> getAllLanguages() {
		Iterable<Topic> topics = getTopicService().getAllTopics();
		return getTopicMapper().mapToDTOList(topics);

	}

}

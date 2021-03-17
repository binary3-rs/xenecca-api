package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.xenecca.api.dto.response.TopicDTO;
import com.xenecca.api.model.Topic;

@Mapper(componentModel = "spring")

public interface TopicMapper {
	TopicDTO mapToDTO(Topic topic);

	List<TopicDTO> mapToDTOList(Iterable<Topic> topics);
}

package com.xenecca.api.dto.response;

import com.xenecca.api.model.Topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO {

	private Long _id;
	private String _name;
	
	public TopicDTO(Topic topic) {
		if(topic != null) {
			_id = topic.getId();
			_name = topic.getName();
		}
	}
}

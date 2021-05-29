package com.xenecca.api.dto.response;

import com.xenecca.api.model.type.MessageStatus;
import com.xenecca.api.model.type.MessageSubject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

	private Long _id;
	private String _name;
	private String _content;
	private String _email;
	private MessageSubject _subject;
	private MessageStatus _status;
}

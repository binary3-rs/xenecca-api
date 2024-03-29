package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.xenecca.api.dto.request.NewMessageDTO;
import com.xenecca.api.dto.response.MessageDTO;
import com.xenecca.api.model.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {
	public Message mapToEntity(NewMessageDTO messageData);

	@Mappings({ @Mapping(target = "subject", expression = "java(message.getSubject().getName())"),
			@Mapping(target = "status", expression = "java(message.getStatus().getName())") })
	public MessageDTO mapToDTO(Message message);

	public List<MessageDTO> mapToDTOList(Iterable<Message> messages);

}

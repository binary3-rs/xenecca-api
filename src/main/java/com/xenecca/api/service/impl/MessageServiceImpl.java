package com.xenecca.api.service.impl;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.MessageRepository;
import com.xenecca.api.dto.request.NewMessageDTO;
import com.xenecca.api.dto.response.ValueMapDTO;
import com.xenecca.api.error.exception.ResourceNotFoundException;
import com.xenecca.api.mapper.MessageMapper;
import com.xenecca.api.model.Message;
import com.xenecca.api.model.type.MessageStatus;
import com.xenecca.api.model.type.MessageSubject;
import com.xenecca.api.service.MessageService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository _messageRepository;

	@Autowired
	private MessageMapper _messageMapper;

	@Override
	public void addMessage(NewMessageDTO messageData) {
		Message message = getMessageMapper().mapToEntity(messageData);
		getMessageRepository().save(message);
	}

	@Override
	public ValueMapDTO getSubjectTypes() {
		return new ValueMapDTO(Arrays.stream(MessageSubject.values())
				.collect(Collectors.toMap(MessageSubject::toString, MessageSubject::getName)));
	}

	@Override
	public Iterable<Message> getAllMessages() {
		return getMessageRepository().findAll();
	}

	@Override
	public Message getMessage(Long id) {
		return getMessageRepository().findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Message not found!"));
	}

	@Override
	public Message changeMessageStatus(Long id, MessageStatus status) {
		Message message = getMessage(id);
		message.setStatus(status);
		return getMessageRepository().save(message);
	}

	@Override
	public void deleteMessage(Long id) {
		getMessageRepository().deleteById(id);

	}

}

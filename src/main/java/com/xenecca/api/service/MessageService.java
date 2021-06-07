package com.xenecca.api.service;

import com.xenecca.api.dto.request.NewMessageDTO;
import com.xenecca.api.dto.response.ValueMapDTO;
import com.xenecca.api.model.Message;
import com.xenecca.api.model.type.MessageStatus;

public interface MessageService {

	public void addMessage(NewMessageDTO messageData);
	
	public ValueMapDTO getSubjectTypes();

	public Iterable<Message> getAllMessages();

	public Message getMessage(Long id);

	public Message changeMessageStatus(Long id, MessageStatus status);

	public void deleteMessage(Long id);

}

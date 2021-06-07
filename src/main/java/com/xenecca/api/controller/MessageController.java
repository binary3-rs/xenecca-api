package com.xenecca.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.ChangeMessageStatusDTO;
import com.xenecca.api.dto.request.NewMessageDTO;
import com.xenecca.api.dto.response.MessageDTO;
import com.xenecca.api.dto.response.ValueMapDTO;
import com.xenecca.api.mapper.MessageMapper;
import com.xenecca.api.service.MessageService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@RestController
public class MessageController {

	@Autowired
	private MessageService _messageService;

	@Autowired
	private MessageMapper _messageMapper;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/contact")
	public void addMessage(@Valid @RequestBody NewMessageDTO messageData) {
		getMessageService().addMessage(messageData);
	}

	@GetMapping("/api/v1/contact/subject-types")
	public ValueMapDTO getSubjectTypes() {
		return getMessageService().getSubjectTypes();
	}

	@GetMapping("/api/v1/messages/")
	public List<MessageDTO> getAllMessages() {
		return getMessageMapper().mapToDTOList(getMessageService().getAllMessages());
	}

	@GetMapping("/api/v1/messages/{id}")
	public MessageDTO getMessage(@PathVariable("id") Long id) {
		return getMessageMapper().mapToDTO(getMessageService().getMessage(id));
	}

	@PatchMapping("/api/v1/messages/{id}")
	public MessageDTO changeMessageStatus(@PathVariable("id") Long id, @RequestBody ChangeMessageStatusDTO statusData) {
		return getMessageMapper().mapToDTO(getMessageService().changeMessageStatus(id, statusData.getStatus()));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/api/v1/messages/{id}")
	public void deleteMessage(@PathVariable("id") Long id) {
		getMessageService().deleteMessage(id);
	}
}

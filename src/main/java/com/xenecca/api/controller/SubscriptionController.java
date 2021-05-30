package com.xenecca.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewSubscriptionDTO;
import com.xenecca.api.dto.response.SubscriptionDTO;
import com.xenecca.api.mapper.SubscriptionMapper;
import com.xenecca.api.service.EmailService;
import com.xenecca.api.service.SubscriptionService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@RestController
@RequestMapping(value = "/api/v1/subscriptions/")
public class SubscriptionController {

	@Autowired
	private SubscriptionService _subscriptionService;

	@Autowired
	private EmailService _emailService;

	@Autowired
	private SubscriptionMapper _subscriptionMapper;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void addSubscription(@Valid @RequestBody NewSubscriptionDTO subscriptionData) {
		getSubscriptionService().addSubscription(subscriptionData);
	}

	@GetMapping
	public List<SubscriptionDTO> getAllSubscriptions() {
		return getSubscriptionMapper().mapToDTOList(getSubscriptionService().getAllSubscriptions());
	}
	
	@DeleteMapping("{token}")
	public boolean unsubscribe(@PathVariable("token") String unsubscribeToken) {
		return getSubscriptionService().unsubscribe(unsubscribeToken);
	}
	
	

	// for testing
//	@GetMapping
//	public void broadcast() {
//		getEmailService().sendEmail("nenadpantelic575@gmail.com", "testit", "subject");

	// }
}

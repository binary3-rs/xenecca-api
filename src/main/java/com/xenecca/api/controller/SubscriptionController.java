package com.xenecca.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xenecca.api.dto.request.NewSubscriptionDTO;
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

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void addSubscription(@Valid @RequestBody NewSubscriptionDTO subscriptionData) {
		getSubscriptionService().addSubscription(subscriptionData);
	}

}

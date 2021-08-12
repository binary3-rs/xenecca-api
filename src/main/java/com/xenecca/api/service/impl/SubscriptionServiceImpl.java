package com.xenecca.api.service.impl;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xenecca.api.dao.SubscriptionRepository;
import com.xenecca.api.dto.request.NewSubscriptionDTO;
import com.xenecca.api.error.exception.SubscriptionAlreadyExistsException;
import com.xenecca.api.model.Subscription;
import com.xenecca.api.service.SubscriptionService;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository _subscriptionRepository;

	@Override
	public void addSubscription(NewSubscriptionDTO subscriptionData) {
		Subscription subscription = Subscription.builder().email(subscriptionData.getEmail()).build();
		try {
			getSubscriptionRepository().save(subscription);
		} catch (ConstraintViolationException | DataIntegrityViolationException ex) {
			throw new SubscriptionAlreadyExistsException("You're already subscribed to our notifications!");
		}
	}

	@Override
	public Iterable<Subscription> getAllSubscriptions() {
		return getSubscriptionRepository().findAll();
	}

	@Override
	public boolean unsubscribe(String unsubscribeToken) {
		Subscription subscription = getSubscriptionRepository().findBy_unsubscribeToken(unsubscribeToken).orElse(null);
		if (subscription != null) {
			getSubscriptionRepository().delete(subscription);
			return true;
		}
		return false;
	}

}

package com.xenecca.api.service;

import com.xenecca.api.dto.request.NewSubscriptionDTO;
import com.xenecca.api.model.Subscription;

public interface SubscriptionService {

	public void addSubscription(NewSubscriptionDTO subscriptionData);

	public Iterable<Subscription> getAllSubscriptions();
	
	public boolean unsubscribe(String unsubscribeToken);

}

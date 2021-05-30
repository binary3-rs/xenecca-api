package com.xenecca.api.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
	public Optional<Subscription> findBy_unsubscribeToken(String token);
	public void deleteBy_unsubscribeToken(String token);
}

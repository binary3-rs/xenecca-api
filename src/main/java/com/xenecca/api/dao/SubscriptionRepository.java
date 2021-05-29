package com.xenecca.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xenecca.api.model.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

}

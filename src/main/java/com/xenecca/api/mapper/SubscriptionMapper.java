package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.xenecca.api.dto.response.SubscriptionDTO;
import com.xenecca.api.model.Subscription;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
	@Mappings({ @Mapping(target = "type", expression = "java(subscription.getType().getName())") })
	public SubscriptionDTO mapToDTO(Subscription subscription);

	public List<SubscriptionDTO> mapToDTOList(Iterable<Subscription> subscriptions);
}

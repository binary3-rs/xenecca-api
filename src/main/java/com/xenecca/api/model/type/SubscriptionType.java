package com.xenecca.api.model.type;

public enum SubscriptionType {

	COURSE("Course");

	private final String _name;

	private SubscriptionType(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
}

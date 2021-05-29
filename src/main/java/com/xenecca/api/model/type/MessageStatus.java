package com.xenecca.api.model.type;

public enum MessageStatus {

	ANSWERED("Answered"), READ("Read"), UNREAD("Unread");

	private final String _name;

	private MessageStatus(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

}

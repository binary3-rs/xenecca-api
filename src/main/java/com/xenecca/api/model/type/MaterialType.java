package com.xenecca.api.model.type;

public enum MaterialType {
	URL("URL"), FILE("FILE");

	private final String _name;

	private MaterialType(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
}

package com.xenecca.api.model.type;

public enum LearningResourceDomain {
	WEBDEV("Web development"), MOBILE("Mobile development"), DEVOPS("DevOps"), DSA("Data structures and algorithsm"),
	SYSDESIGN("System design and software architecture"), DATASCIENCE("Data science"), DATABASES("Databases");

	private final String _name;

	private LearningResourceDomain(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
}

package com.xenecca.api.model.type;

public enum LearningResourceDomain {
	COMPUTER_SCIENCE("Computer science"), DATABASES("Databases"), DATASCIENCE("Data science"), DEVOPS("DevOps"),
	DIGITAL_MARKETING("Digital marketing"), DSA("Data structures and algorithsm"), MOBILE("Mobile development"),
	OTHER("Other"), PROGRAMMING_LANGUAGES("Programming languages"), SOFTWARE_DESIGN("Software design and architecture"),
	UI_AND_UX("UI & UX"), WEBDEV("Web development");

	private final String _name;

	private LearningResourceDomain(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
}
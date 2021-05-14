package com.xenecca.api.model.type;

public enum LearningResourceDomain {
	WEBDEV("Web development"), MOBILE("Mobile development"), DEVOPS("DevOps"), DSA("Data structures and algorithsm"),
	SOFTWARE_DESIGN("Software design and architecture"), DATASCIENCE("Data science"), DATABASES("Databases"),
	PROGRAMMING_LANGUAGES("Programming languages"), UI_AND_UX("UI & UX"), COMPUTER_SCIENCE("Computer science"),
	DIGITAL_MARKETING("Digital marketing"), OTHER("Other");

	private final String _name;

	private LearningResourceDomain(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
}
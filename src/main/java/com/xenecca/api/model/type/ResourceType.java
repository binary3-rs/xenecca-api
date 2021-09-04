package com.xenecca.api.model.type;

public enum ResourceType {
	CHEATSHEET("Cheatsheet"), BOOK("Book"), SCRIPT("Script"), IMAGE("Image"), BLOG_OR_ARTICLE("Blog/article"),
	TUTORIAL_OR_COURSE("Tutorial/course"), WEBSITE("Website"), PODCAST("Podcast"), PROJECT_OR_APP("Project/app"),
	COLLECTION("Collection");

	private final String _name;

	ResourceType(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
}

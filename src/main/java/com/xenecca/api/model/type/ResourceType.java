package com.xenecca.api.model.type;

public enum ResourceType {
	CHEATSHEET("Cheatsheet"), BOOK("Book"), SCRIPT("Script"), IMAGE("Image"), BLOG_OR_ARTICLE("Blog/article"),
	TUTORIAL("Tutorial"), WEBSITE("Website"), PODCAST("Podcast"), PROJECT_OR_APP("Project/app"),
	COLLECTION("Collection");

	private final String _name;

	private ResourceType(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
}

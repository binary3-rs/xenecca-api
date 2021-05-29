package com.xenecca.api.model.type;

public enum MessageSubject {
	ASK_FOR_HELP("Ask for help"), MAKE_A_SUGGESTION("Make a suggestion"),
	OTHER("Other"), REPORT_A_BUG_OR_PROBLEM("Report a problem/bug");

	private final String _name;

	private MessageSubject(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
}
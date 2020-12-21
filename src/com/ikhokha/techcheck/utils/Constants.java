package com.ikhokha.techcheck.utils;

public interface Constants {

	interface CommentIdentifiers {
		String MOVER_MENTIONS = "MOVER_MENTIONS";
		String SHAKER_MENTIONS = "SHAKER_MENTIONS";
		String SHORTER_THAN_15 = "SHORTER_THAN_15";
	}
	
	interface CommentEnhancementIdentifers {
		String SPAM = "SPAM";
		String QUESTIONS = "QUESTIONS";
	}
	
	interface UrlRegexRules {
		final String URL_REGEX = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";
	}
}

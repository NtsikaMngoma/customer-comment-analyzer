package com.ikhokha.techcheck.utils;

public class ThreadRuntimeValidator {

	public static Integer coresInEnv() {
		return Runtime.getRuntime().availableProcessors();
	}
}

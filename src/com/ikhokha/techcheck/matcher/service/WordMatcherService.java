package com.ikhokha.techcheck.matcher.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ikhokha.techcheck.matcher.contract.IMatcherContract;

public class WordMatcherService implements IMatcherContract {
	private String _keyString;
	private String _regexString;
	
	public WordMatcherService(String keyString, String regexString) throws Exception {
		boolean validateByMatcher = regexString.isEmpty() || keyString.isEmpty();
		if (validateByMatcher) {
			throw new Exception("The report key or regex cannot be empty");
		}
		_keyString = keyString;
		_regexString = regexString;
	}

	@Override
	public String getReportKey() {
		// TODO Auto-generated method stub
		return _keyString;
	}

	@Override
	public int count(String commString) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile(_regexString, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(commString);
		return matcher.find() ? 1 : 0;
	}
}

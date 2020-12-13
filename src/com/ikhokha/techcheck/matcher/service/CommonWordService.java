package com.ikhokha.techcheck.matcher.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ikhokha.techcheck.matcher.contract.IPatternMatcher;

public class CommonWordService implements IPatternMatcher {

	private String _key;
	private String _keyword;
	
	
	public CommonWordService(String key, String keyword) {
		_key = key;
		_keyword = keyword;
	}

	@Override
	public String getReportById() {
		// TODO Auto-generated method stub
		return _key;
	}

	@Override
	public int count(String commString) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile(_keyword, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(commString);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

}

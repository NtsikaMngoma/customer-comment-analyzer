package com.ikhokha.techcheck.matcher.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ikhokha.techcheck.matcher.contract.IMatcherContract;

public class CommonWordService implements IMatcherContract {

	private String _key;
	private String _keyword;
	
	
	public CommonWordService(String key, String keyword) throws Exception {
		if(key.isEmpty() || keyword.isEmpty()){
            throw new Exception("Report key or search keyword cannot be empty");
        }
		_key = key;
		_keyword = keyword;
	}

	@Override
	public String getReportKey() {
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

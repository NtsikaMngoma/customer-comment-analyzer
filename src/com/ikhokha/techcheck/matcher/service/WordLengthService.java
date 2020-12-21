package com.ikhokha.techcheck.matcher.service;
import com.ikhokha.techcheck.matcher.contract.IMatcherContract;

public class WordLengthService implements IMatcherContract {
	private String _keyString;
	private int _length;
	public static enum Condition {
		LessThan,
		EqualTo,
		MoreThan
	}
	private Condition _condition;

	public WordLengthService(String keyString, Condition condition, int length) throws Exception {
		boolean validLengthVal = length < 1;
		if (validLengthVal) {
			throw new Exception("Condition length cannot be less than 1");
		} else if (keyString.isEmpty()){  
			throw new Exception("Report key can't be empty");
		}
		_keyString = keyString;
		_length = length;
		_condition = condition;
	}

	@Override
	public String getReportKey() {
		// TODO Auto-generated method stub
		return _keyString;
	}

	@Override
	public int count(String commString) {
		// TODO Auto-generated method stub
		switch (_condition) {
		case EqualTo:
			return commString.length() == _length ? 1 : 0;
		case LessThan: 
			return commString.length() < _length ? 1 : 0;
		case MoreThan: 
			return commString.length() < _length ? 1 : 0;
		default:
			break;
		} 
		return 0;
	}
}

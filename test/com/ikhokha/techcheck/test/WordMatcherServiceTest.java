package com.ikhokha.techcheck.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.ikhokha.techcheck.matcher.service.WordMatcherService;
import com.ikhokha.techcheck.test.utils.Constants;

public class WordMatcherServiceTest {
	private WordMatcherService wordMatcherTest;
	
	@Test
	public void whenGetReportKeyIsInvokedThenCorrectKeyIsReturned() throws Exception {
		wordMatcherTest = new WordMatcherService(Constants.THE_REPORT_KEY, "[?]");
		assertFalse(wordMatcherTest.getReportKey().isEmpty());
		assertEquals(wordMatcherTest.getReportKey(), Constants.THE_REPORT_KEY);
	}
	
	@Test
    public void whenQuestionsCountIsInvokedThenCorrectOccurrenceIsReturned() throws Exception
    {
		wordMatcherTest = new WordMatcherService(Constants.THE_REPORT_KEY, "[?]");
        int count = wordMatcherTest.count(Constants.Comments.WORD_MATCHER_QUESTION);
        int thanks = wordMatcherTest.count(Constants.Comments.WORD_MATCHER_COMMENT);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }

    @Test
    public void whenSpamCountIsInvokedThenCorrectOccurrenceIsReturned() throws Exception
    {
    	wordMatcherTest = new WordMatcherService(Constants.THE_REPORT_KEY, com.ikhokha.techcheck.utils.Constants.UrlRegexRules.URL_REGEX);
        int count = wordMatcherTest.count(Constants.Comments.SPAM);
        int thanks = wordMatcherTest.count(Constants.Comments.WORD_MATCHER_COMMENT);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }

    @Test(expected = Exception.class)
    public void whenReportKeyIsEmptyThenExceptionIsThrown() throws Exception {
    	wordMatcherTest = new WordMatcherService("", "");
        assertNull(wordMatcherTest);
    }
}

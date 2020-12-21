package com.ikhokha.techcheck.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.ikhokha.techcheck.matcher.service.WordLengthService;
import com.ikhokha.techcheck.test.utils.Constants;

public class WordLengthServiceTest {
	private WordLengthService wordLength;
	
    @Test
    public void whenGetReportKeyIsInvokedThenCorrectKeyIsReturned() throws Exception
    {
        wordLength = new WordLengthService(Constants.REPORT_KEY, WordLengthService.Condition.LessThan, 15);
        assertFalse(wordLength.getReportKey().isEmpty());
        assertEquals(wordLength.getReportKey(),Constants.REPORT_KEY);
    }

    @Test
    public void whenLessThanCountIsInvokedThenCorrectOccurrenceIsReturned() throws Exception
    {
        wordLength = new WordLengthService(Constants.REPORT_KEY, WordLengthService.Condition.LessThan, 15);
        int count = wordLength.count(Constants.Comments.IKHOKHA);
        int thanks = wordLength.count(Constants.Comments.LENGTH_COMMENT);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }

    @Test
    public void whenMoreThanCountIsInvokedThenCorrectOccurrenceIsReturned() throws Exception
    {
        wordLength = new WordLengthService(Constants.REPORT_KEY, WordLengthService.Condition.MoreThan, 15);
        int count = wordLength.count(Constants.Comments.IKHOKHA);
        int thanks = wordLength.count(Constants.Comments.LENGTH_COMMENT);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }

    @Test
    public void whenEqualCountIsInvokedThenCorrectOccurrenceIsReturned() throws Exception
    {
        wordLength = new WordLengthService(Constants.REPORT_KEY, WordLengthService.Condition.EqualTo, 7);
        int count = wordLength.count(Constants.Comments.IKHOKHA);
        int thanks = wordLength.count(Constants.Comments.LENGTH_COMMENT);
        assertEquals(count,1);
        assertEquals(thanks,0);
    }


    @Test(expected = Exception.class)
    public void whenReportKeyIsEmptyThenExceptionIsThrown() throws Exception {
        wordLength = new WordLengthService(Constants.ZERO_LENGTH, WordLengthService.Condition.LessThan, 0);
        assertNull(wordLength);

        wordLength = new WordLengthService("", WordLengthService.Condition.MoreThan, 10);
        assertNull(wordLength);
    }
}

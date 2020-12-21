package com.ikhokha.techcheck.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.ikhokha.techcheck.matcher.service.CommonWordService;
import com.ikhokha.techcheck.test.utils.Constants;

public class CommonWordServiceTest {
	CommonWordService commonWord;
    @Before
    public void setup() throws Exception {
        commonWord = new CommonWordService(Constants.KEY, "Shaker");
    }

    @Test
    public void whenGetReportKeyIsInvokedThenCorrectKeyIsReturned()
    {
        assertFalse(commonWord.getReportKey().isEmpty());
        assertEquals(commonWord.getReportKey(),Constants.KEY);
    }

    @Test
    public void whenCountIsInvokedThenCorrectOccurrenceIsReturned()
    {
        int count = commonWord.count(Constants.Comments.COMMON_WORD_COMMENT);
        int zero = commonWord.count(Constants.Comments.COMMON_NO_MENTIONS_COMMENT);
        assertEquals(count,2);
        assertEquals(zero,0);
    }

    @Test(expected = Exception.class)
    public void whenReportKeyIsEmptyThenExceptionIsThrown() throws Exception {
        commonWord = new CommonWordService("", "");
        assertNull(commonWord);
    }
}

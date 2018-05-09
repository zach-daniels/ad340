package com.example.zach.ad340_mainapp;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HomePageTest {

    private String emptyMessage      = "";
    private String validMessage      = "Message";
    private String whiteSpaceMessage = " ";

    private HomePage testPage = new HomePage();

    @Test
    public void testMessageValidationEmptyString() {
        assertFalse(testPage.validateMessage(emptyMessage));
    }

    @Test
    public void testMessageValidationString() {
        assertTrue(testPage.validateMessage(validMessage));
    }

    @Test
    public void testMessageValidationWhiteSpace() {
        assertFalse(testPage.validateMessage(whiteSpaceMessage));
    }
}
package com.example.zach.ad340_mainapp;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HomePageTest {
    private HomePage testPage = new HomePage();

    @Test
    public void testMessageValidationEmptyString() {
        String message = "";
        assertFalse(testPage.validateMessage(message));
    }

    @Test
    public void testMessageValidationString() {
        String message = "Message";
        assertTrue(testPage.validateMessage(message));
    }

    @Test
    public void testMessageValidationWhiteSpace() {
        String message = " ";
        assertFalse(testPage.validateMessage(message));
    }
}
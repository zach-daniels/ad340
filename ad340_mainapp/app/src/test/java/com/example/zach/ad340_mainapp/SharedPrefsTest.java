package com.example.zach.ad340_mainapp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.SharedPreferences;

@RunWith(MockitoJUnitRunner.class)
public class SharedPrefsTest {

    @Mock
    SharedPreferences myMockSharedPrefs;

    @Mock
    SharedPreferences.Editor myMockEditor;

    private SharedPreferencesWrapper myMockSharedPrefsHelper;

    private String valid_entry = "Please work!";

    @Before
    public void initMocks() {
        myMockSharedPrefsHelper = createMockSharedPrefs();
    }

    @Test
    public void sharedPrefsWriteTest() {
        boolean success = myMockSharedPrefsHelper.savePreferences(valid_entry);
        assertTrue(success);
    }

    @Test
    public void sharedPrefsReadTest() {
        assertEquals(valid_entry, myMockSharedPrefsHelper.getPreferences());
    }

    private SharedPreferencesWrapper createMockSharedPrefs() {

        // Mock reading a SharedPref as if the Mock was written correctly
        when(myMockSharedPrefs.getString(eq("valid_entry"), anyString()))
                .thenReturn(valid_entry);

        // Mock successful commit
        when(myMockEditor.commit()).thenReturn(true);

        // Return MockEditor
        when(myMockSharedPrefs.edit()).thenReturn(myMockEditor);

        return new SharedPreferencesWrapper(myMockSharedPrefs);
    }
}

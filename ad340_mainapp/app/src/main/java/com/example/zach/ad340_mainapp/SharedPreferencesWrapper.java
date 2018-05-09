package com.example.zach.ad340_mainapp;

import android.content.SharedPreferences;

public class SharedPreferencesWrapper {

    static final String KEY = "valid_entry";
    static final String DEFAULT = "Enter a message";

    private final SharedPreferences mySharedPreferences;

    public SharedPreferencesWrapper(SharedPreferences sharedPreferences) {
        mySharedPreferences = sharedPreferences;
    }

    public boolean savePreferences(String message) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(KEY, message);
        return editor.commit();
    }

    public String getPreferences() {
        return mySharedPreferences.getString(KEY, DEFAULT);
    }
}

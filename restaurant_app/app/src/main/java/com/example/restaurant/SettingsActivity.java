package com.example.restaurant;


import android.os.Bundle;

public class SettingsActivity extends RegisterActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityInit();
        registerBtn.setText("Save");
    }
}
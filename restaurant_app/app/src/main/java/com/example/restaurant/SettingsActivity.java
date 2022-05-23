package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SettingsActivity extends RegisterActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityInit();
        registerBtn.setText("Save");
        registerBtn.setText("hello");
    }
}
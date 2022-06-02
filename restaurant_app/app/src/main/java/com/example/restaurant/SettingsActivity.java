package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends RegisterActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityInit();
        setContentView(registerContent);
        nameTxtField.setText(User.getUserFirstName());
        surnameTxtField.setText(User.getUserSurname());
        userNameTxtField.setText(User.getUserLoginUsername());
        passwordTxtField.setText(User.getUserLoginPassword());
        phoneNumTxtField.setText(User.getUserPhoneNumber());
        registerBtn.setText("Save");
    }

    private void addDetails(){
        User user = new User();
    }
}

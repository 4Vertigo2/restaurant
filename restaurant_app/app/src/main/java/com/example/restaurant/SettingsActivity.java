package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends RegisterActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityInit();
        TextView successText = new TextView(this);
        registerContent.addView(successText);

        setContentView(registerContent);
        nameTxtField.setText(User.getUserFirstName());
        surnameTxtField.setText(User.getUserSurname());
        usernameTxtField.setText(User.getUserLoginUsername());
        passwordTxtField.setText(User.getUserLoginPassword());
        phoneNumTxtField.setText(User.getUserPhoneNumber());
        registerBtn.setText("Save");
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                successText.setText("");
                successText.setText("Updated Successfully");
                updateDetails();
            }
        });
    }

    private void updateDetails(){
        User.setUserFirstName(nameTxtField.getText().toString());
        User.setUserSurname(surnameTxtField.getText().toString());
        User.setUserLoginUsername(usernameTxtField.getText().toString());
        User.setUserLoginPassword(passwordTxtField.getText().toString());
        User.setUserPhoneNumber(phoneNumTxtField.getText().toString());
    }
}

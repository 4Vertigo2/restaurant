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
        setTitle("Takes");
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
                if(isUserInDatabase(usernameTxtField.getText().toString(), passwordTxtField.getText().toString())){
                    if(validateAllInput()){
                        successText.setText("Updated Successfully");
                        updateDetails();
                    }
                    else{
                        successText.setText("Please make sure that all your details are correct ");
                    }
                }
                else{
                    successText.setText("Username and Password is already taken.");
                }
            }
        });
    }

    private void updateDetails(){
        User.updateUser(this,nameTxtField.getText().toString(),surnameTxtField.getText().toString(),usernameTxtField.getText().toString(),passwordTxtField.getText().toString(),phoneNumTxtField.getText().toString());

    }
}

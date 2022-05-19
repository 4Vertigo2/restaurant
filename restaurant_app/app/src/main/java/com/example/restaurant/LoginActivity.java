package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    private LinearLayout loginContent;
    private TextView appNameLbl;
    private EditText userNameTxtField, passwordTxtField;
    private Button loginBtn;

    private void loginInit(){
        loginContent = new LinearLayout(this);
        appNameLbl = new TextView(this);
        userNameTxtField = new EditText(this);
        passwordTxtField = new EditText(this);
        loginBtn = new Button(this);

        loginContent.setOrientation(LinearLayout.VERTICAL);
        appNameLbl.setText("Login");
        userNameTxtField.setHint("Username");
        passwordTxtField.setHint("Password");
        passwordTxtField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        loginBtn.setText("Login");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginInit();
        loginContent.addView(appNameLbl);
        loginContent.addView(userNameTxtField);
        loginContent.addView(passwordTxtField);
        loginContent.addView(loginBtn);
        
        setContentView(loginContent);

    }
}
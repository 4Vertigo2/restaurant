package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    private LinearLayout loginContent;
    private TextView appNameLbl;
    private EditText userNameTxtField, passwordTxtField;
    private Button loginBtn, registerBtn;

    private void loginInit(){
        //initializes views
        loginContent = new LinearLayout(this);
        appNameLbl = new TextView(this);
        userNameTxtField = new EditText(this);
        passwordTxtField = new EditText(this);
        loginBtn = new Button(this);
        registerBtn = new Button(this);
        //SpannableString span = new SpannableString("Register");

        //sets up views
        loginContent.setOrientation(LinearLayout.VERTICAL);
        appNameLbl.setText("[Restaurant App Name]");
        userNameTxtField.setHint("Username");
        passwordTxtField.setHint("Password");
        passwordTxtField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        loginBtn.setText("Login");
        //used to underline TextView registerBtn
        //span.setSpan(new UnderlineSpan(),0,span.length(),0);
        registerBtn.setText("Register");
    }

    private void loginAddViews(){
        loginContent.addView(appNameLbl);
        loginContent.addView(userNameTxtField);
        loginContent.addView(passwordTxtField);
        loginContent.addView(loginBtn);
        loginContent.addView(registerBtn);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginInit();
        loginAddViews();
        setContentView(loginContent);
        Help.goToActivity(this,registerBtn, new RegisterActivity());

    }


}
package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Rating;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private LinearLayout loginContent;
    private TextView appNameLbl;
    private TextView userNotFoundLbl;
    private EditText usernameTxtField, passwordTxtField;
    private Button loginBtn, registerBtn;

    //initializes the default values of the views used
    private void loginInit(){
        //sets up views
        loginContent.setOrientation(LinearLayout.VERTICAL);
        setTitle("Takes");
        usernameTxtField.setHint("Username");
        passwordTxtField.setHint("Password");
        passwordTxtField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        loginBtn.setText("Login");
        //used to underline TextView registerBtn
        //span.setSpan(new UnderlineSpan(),0,span.length(),0);
        registerBtn.setText("Register");
        userNotFoundLbl.setText("");
    }

    //adds views to activity
    private void loginAddViews(){
        loginContent.addView(appNameLbl);
        loginContent.addView(usernameTxtField);
        loginContent.addView(passwordTxtField);
        loginContent.addView(loginBtn);
        loginContent.addView(registerBtn);
        loginContent.addView(userNotFoundLbl);
    }


    //checks to see if a user exists, logs them in if they do.
    private void loginUser(String username, String password){
        Intent intent;
        User.userInit(this,username,password);
        userNotFoundLbl.setText("");

        //removes all previous views so as to make everything blank again
        if(!User.getUserExists()){
           userNotFoundLbl.setText("Username or Password is incorrect, please try again.");
           return;
        }

        if(User.getUserLoginStaff()){
           intent = new Intent(this, StaffActivity.class);
           intent.putExtra("staffID",User.getUserID());
           startActivity(intent);
           return;
        }

        intent = new  Intent(this, CustomerOrderActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializes views
        loginContent = new LinearLayout(this);
        appNameLbl = new TextView(this);
        usernameTxtField = new EditText(this);
        usernameTxtField.setSingleLine();
        passwordTxtField = new EditText(this);
        passwordTxtField.setSingleLine();
        loginBtn = new Button(this);
        registerBtn = new Button(this);
        userNotFoundLbl = new TextView(this);
        //SpannableString span = new SpannableString("Register");


        //adds default values to views and adds them to the Activity
        loginInit();
        loginAddViews();
        setContentView(loginContent);

        Help.goToActivity(this, registerBtn, new RegisterActivity());
        disableAutoFill();
        //when the login button is pressed, take the inputs sends them to the login function
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(usernameTxtField.getText().toString(), passwordTxtField.getText().toString());
            }
        });
        //loginUser("FridgeMan","Fr1dg3Man69");
        Help.enterKey(passwordTxtField);
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void disableAutoFill() {
        getWindow().getDecorView().setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS);
    }

}
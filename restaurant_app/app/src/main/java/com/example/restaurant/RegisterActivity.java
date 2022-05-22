package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private LinearLayout registerContent;
    private EditText nameTxtField,surnameTxtField,userNameTxtField,passwordTxtField, phoneNumTxtField;
    private CheckBox staffBtn;
    Button registerBtn;

    private void registerAddViews(){
        registerContent.addView(nameTxtField);
        registerContent.addView(surnameTxtField);
        registerContent.addView(userNameTxtField);
        registerContent.addView(passwordTxtField);
        registerContent.addView(phoneNumTxtField);
        registerContent.addView(staffBtn);
        registerContent.addView(registerBtn);
    }
    public void activityInit(){
        //initializes views
        registerContent = new LinearLayout(this);
        nameTxtField = new EditText(this);
        surnameTxtField = new EditText(this);
        userNameTxtField = new EditText(this);
        passwordTxtField = new EditText(this);
        phoneNumTxtField = new EditText(this);
        staffBtn = new CheckBox(this);
        registerBtn = new Button(this);

        //sets up views
        registerContent.setOrientation(LinearLayout.VERTICAL);
        nameTxtField.setHint("Name");
        surnameTxtField.setHint("Surname");
        userNameTxtField.setHint("Username");
        passwordTxtField.setHint("Password");
        passwordTxtField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        phoneNumTxtField.setHint("Phone number");
        phoneNumTxtField.setInputType(InputType.TYPE_CLASS_PHONE);
        staffBtn.setText("Staff");
        registerBtn.setText("Register");

        registerAddViews();
        setContentView(registerContent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInit();
        Help.goToActivity(this,registerBtn,new LoginActivity());

    }
}
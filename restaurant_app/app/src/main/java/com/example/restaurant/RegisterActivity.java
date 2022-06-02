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
import java.util.regex.*;

public class RegisterActivity extends AppCompatActivity {
    LinearLayout registerContent;
    EditText nameTxtField,surnameTxtField,userNameTxtField,passwordTxtField, phoneNumTxtField;
    private CheckBox staffChkBox;
    Button registerBtn;

    private void registerAddViews(){
        registerContent.addView(nameTxtField);
        registerContent.addView(surnameTxtField);
        registerContent.addView(userNameTxtField);
        registerContent.addView(passwordTxtField);
        registerContent.addView(phoneNumTxtField);
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
        registerBtn.setText("Register");

        registerAddViews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInit();

        staffChkBox = new CheckBox(this);
        registerContent.addView(staffChkBox);
        staffChkBox.setText("Staff");


        setContentView(registerContent);

        Help.goToActivity(this,registerBtn,new LoginActivity());

    }
    //checks if field is left blank after trimming whitespaces
    private boolean isBlankChk(String string){
        return string.trim().isEmpty();
    }

    /*Minimum eight characters,
    maximum of 50 characters,
    at least one uppercase letter,
    one lowercase letter
    and one number:
     */

    private boolean passwordChk(String password){
        //compiles regex
        Pattern passPat = Pattern.compile("^(?=.[a-z])(?=.[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,50}$");
        //searches for the pattern in the string
        Matcher match = passPat.matcher(password);
        //match.find() returns whether the pattern was found in the password.
        return match.find();
    }
    /*
    has to start with 0,
    second character 8, 7 or 6.
    has to be at most 8 other characters
     */

    private boolean phoneNumCheck(String phoneNum){

        Pattern  phonePat = Pattern.compile("^(\0)[6-8][0-9]{8}$");
        Matcher match = phonePat.matcher(phoneNum);
        return match.find();
    }

}
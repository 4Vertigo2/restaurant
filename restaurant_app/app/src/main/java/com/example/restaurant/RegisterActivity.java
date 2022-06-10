package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.regex.*;

public class RegisterActivity extends AppCompatActivity {
    LinearLayout registerContent;
    EditText nameTxtField,surnameTxtField,usernameTxtField,passwordTxtField, phoneNumTxtField,staffRestaurantTxt;
    private CheckBox staffChkBox;
    Button registerBtn;

    private void registerAddViews(){
        registerContent.addView(nameTxtField);
        registerContent.addView(surnameTxtField);
        registerContent.addView(usernameTxtField);
        registerContent.addView(passwordTxtField);
        registerContent.addView(phoneNumTxtField);
        registerContent.addView(registerBtn);
    }
    public void activityInit(){
        //initializes views
        registerContent = new LinearLayout(this);
        nameTxtField = new EditText(this);
        nameTxtField.setSingleLine();
        surnameTxtField = new EditText(this);
        surnameTxtField.setSingleLine();
        usernameTxtField = new EditText(this);
        usernameTxtField.setSingleLine();
        passwordTxtField = new EditText(this);
        passwordTxtField.setSingleLine();
        phoneNumTxtField = new EditText(this);
        phoneNumTxtField.setSingleLine();
        registerBtn = new Button(this);

        staffRestaurantTxt = new EditText(this);
        //sets up views
        registerContent.setOrientation(LinearLayout.VERTICAL);
        nameTxtField.setHint("Name");
        surnameTxtField.setHint("Surname");
        usernameTxtField.setHint("Username");
        passwordTxtField.setHint("Password");
        passwordTxtField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        phoneNumTxtField.setHint("Phone number");
        phoneNumTxtField.setInputType(InputType.TYPE_CLASS_PHONE);
        registerBtn.setText("Register");

        staffRestaurantTxt.setHint("Enter Restaurant ID");
staffRestaurantTxt.setInputType(InputType.TYPE_CLASS_NUMBER);
        registerAddViews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Takes");
        activityInit();

        TextView successText = new TextView(this);
        registerContent.addView(successText);

        staffChkBox = new CheckBox(this);
        registerContent.addView(staffChkBox);
        staffChkBox.setText("Staff");


        Help.goToActivity(this,registerBtn,new LoginActivity());

        staffChkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(staffChkBox.isChecked()){
                    registerContent.addView(staffRestaurantTxt);
                    setContentView(registerContent);
                }
                else{
                    registerContent.removeView(staffRestaurantTxt);
                }

            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                successText.setText("");
                if(!isUserInDatabase(usernameTxtField.getText().toString(), passwordTxtField.getText().toString())) {
                    if(!validateAllInput()){
                        successText.setText("Please make sure all the details are correct.");
                        Toast.makeText(RegisterActivity.this, "Password must contain 8 characters, capital, lowercase and digit characters", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        register();
                        successText.setText("You have been registered");
                    }
                }
                else{
                    successText.setText("You are already registered.");
                }
            }
        });
        Help.enterKey(nameTxtField);

        setContentView(registerContent);
    }

    private void register(){
        User.registerUser(this, nameTxtField.getText().toString(), surnameTxtField.getText().toString(), usernameTxtField.getText().toString(), passwordTxtField.getText().toString(), phoneNumTxtField.getText().toString(), staffChkBox.isChecked(),staffRestaurantTxt.getText().toString());
    }

    public boolean isUserInDatabase(String username, String password){
       User.userInit(this, username, password);
       return User.getUserExists();
    }

    public boolean validateAllInput(){

        if(Validation.isBlankChk(nameTxtField.getText().toString()) || Validation.isBlankChk(surnameTxtField.getText().toString()) || Validation.isBlankChk(usernameTxtField.getText().toString()) || Validation.isBlankChk(passwordTxtField.getText().toString()) || Validation.isBlankChk(phoneNumTxtField.getText().toString())){
            return false;
        }
        if(!Validation.phoneNumCheck(phoneNumTxtField.getText().toString())){
            return false;
        }
        if(!Validation.passwordChk(passwordTxtField.getText().toString())){
            return false;
        }

        return true;
    }
}

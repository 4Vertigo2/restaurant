package com.example.restaurant;

import android.app.Activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    //checks if field is left blank after trimming whitespaces
    public static boolean isBlankChk(String string){
        return string.trim().isEmpty();
    }

    /*Minimum eight characters,
    maximum of 50 characters,
    at least one uppercase letter,
    one lowercase letter
    and one number:
     */

    public static boolean passwordChk(String password){
        //compiles regex""
        Pattern passPat = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
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

    public static boolean phoneNumCheck(String phoneNum){
        Pattern  phonePat = Pattern.compile("^(\\+27|0)[6-8][0-9]{8}$");
        Matcher match = phonePat.matcher(phoneNum);
        return match.find();
    }


}

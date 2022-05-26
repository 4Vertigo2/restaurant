package com.example.restaurant;

import android.app.Activity;
import android.content.ContentValues;
import org.json.*;

import java.util.ArrayList;

public class User {
    private static PHPRequest php = new PHPRequest();

    private static boolean userExists;
    private static int userID;
    private static String userFirstName;
    private static String userSurname;
    private static String userPhoneNumber;
    private static int userLoginID;
    private static String userLoginUsername;
    private static String userLoginPassword;
    private static boolean userLoginStaff;
    private static int staffRestaurantID;

    private static JSONArray arr;

    public static void userInit(Activity act, String username, String password){

        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);

        php.doRequest(act, "php_login", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                try {
                    arr = new JSONArray(response);

                    if (arr.length() > 0) {
                        userExists = true;
                        processResponse(response);
                    } else {
                        userExists = false;
                    }
                } catch (JSONException e) {
                    System.out.println("User Class : Json failed");
                }
            }
        }
        );
    }
    private static void processUserResponse(String response){
        try{
            JSONObject loginData = (JSONObject) arr.get(1);
            JSONObject userData = (JSONObject) arr.get(2);

            userLoginID = loginData.getInt("LOGIN_ID");
            userLoginUsername = loginData.getString("LOGIN_USERNAME");
            userLoginPassword = loginData.getString("LOGIN_PASSWORD");
            userLoginStaff = loginData.getBoolean("LOGIN_STAFF");

            if(userLoginStaff){
                userID = userData.getInt("STAFF_ID");
                userFirstName = userData.getString("STAFF_FIRST_NAME");
                userSurname = userData.getString("STAFF_SURNAME");
                userPhoneNumber = userData.getString("STAFF_PHONE_NUMBER");
                staffRestaurantID = userData.getInt("RESTAURANT_ID");
            }
            else{
              userID = userData.getInt("CUSTOMER_ID");
              userFirstName = userData.getString("CUSTOMER_FIRST_NAME");
              userSurname = userData.getString("CUSTOMER_SURNAME");
              userPhoneNumber = userData.getString("CUSTOMER_PHONE_NUMBER");
            }
        }
        catch(JSONException e){
            System.out.println("User Class: unexpected Json exception");
        }
    }

   public static void userInsertData(Activity act){
        ContentValues cv = new ContentValues();
        cv.put("user_first_name",getUserFirstName());
        cv.put("user_surname", getUserSurname());
        cv.put("user_login_username", getUserLoginUsername());
        cv.put("user_login_password",getUserLoginPassword());
        cv.put("user_phone_number",getUserPhoneNumber());

        if(getUserLoginStaff()){
        cv.put("user_login_staff",getUserLoginStaff());
        cv.put("user_restaurant_id",getStaffRestaurantID());
        php.doRequest(act,"php_staff_insert",cv,null);
        return;
        }

        php.doRequest(act,"php_customer_insert",cv,null);
   }




    public static int getUserID(){
        return userID;
    }

    public static boolean getUserExists() {
        return userExists;
    }

    public static void setUserID(int userID) {
        User.userID = userID;
    }

    public static String getUserFirstName() {
        return userFirstName;
    }

    public static void setUserFirstName(String userFirstName) {
        User.userFirstName = userFirstName;
    }

    public static String getUserSurname() {
        return userSurname;
    }

    public static void setUserSurname(String userSurname) {
        User.userSurname = userSurname;
    }

    public static String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public static void setUserPhoneNumber(String userPhoneNumber) {
        User.userPhoneNumber = userPhoneNumber;
    }

    public static int getUserLoginID() {
        return userLoginID;
    }

    public static void setUserLoginID(int userLoginID) {
        User.userLoginID = userLoginID;
    }

    public static String getUserLoginUsername() {
        return userLoginUsername;
    }

    public static void setUserLoginUsername(String userLoginUsername) {
        User.userLoginUsername = userLoginUsername;
    }

    public static String getUserLoginPassword() {
        return userLoginPassword;
    }

    public static void setUserLoginPassword(String userLoginPassword) {
        User.userLoginPassword = userLoginPassword;
    }

    public static boolean getUserLoginStaff() {
        return userLoginStaff;
    }

    public static void setUserLoginStaff(boolean userLoginStaff) {
        User.userLoginStaff = userLoginStaff;
    }

    public static int getStaffRestaurantID() {
        return staffRestaurantID;
    }

    public static void setStaffRestaurantID(int staffRestaurantID) {
        User.staffRestaurantID = staffRestaurantID;
    }

}

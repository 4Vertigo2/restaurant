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

    /*checks if a user is in the database. If they are, runs the code to process json and assign to variables above
    if they aren't, sets userExists to false which is used in by the Login Page to update it respectively.
     */
    public static void userInit(Activity act, String username, String password){

        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);

        php.doRequest(act, "login", cv, response -> {
            try {
                arr = new JSONArray(response);

                /*when json response fails, it returns an empty array. It will either find
                something or it won't as login records are unique*/
                if (arr.length() > 0) {
                    userExists = true;
                    processUserResponse();
                }
                else{
                    userExists = false;
                }
            } catch (JSONException e) {
                System.out.println("User Class : Json failed");
            }
        }
        );
    }

    /*the following code is used to process the Json response of all the data about the user,


    when json is sent from the server, it is sent as a string map which can be parsed into an
    JSONArray or JSONObject, which can then be used to extract the data with the Keys.
     */
    private static void processUserResponse(){
        try{
            /*Json response comes in an array as we're extracting all the data from 2 tables
              first array in the response is from the LOGIN_TBL
              second array is all the user data, either from Staff or Customer tbl's*/
            String userLoginStaffString;
            JSONObject loginData = (JSONObject) arr.get(0);
            JSONObject userData = (JSONObject) arr.get(1);

            //both Customers and Staff have the same login data
            userLoginID = loginData.getInt("LOGIN_ID");
            userLoginUsername = loginData.getString("LOGIN_USERNAME");
            userLoginPassword = loginData.getString("LOGIN_PASSWORD");
            userLoginStaffString = loginData.getString("LOGIN_STAFF");

            //.getBool was not working correctly, assigning it manually.
            if(userLoginStaffString.equals("1")){
                userLoginStaff = true;
            }
            else{
                userLoginStaff = false;
            }


            /*STAFF and CUSTOMER tbl's have different field names,
            hence why we needed to process them separately.
            STAFF_TBL also has a few extra columns.
             */

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

    /*used when the user updates their data in the settings page
    we send 1 response updating everything.

    the following code is also be used for inserting brand new users in the register page
    however, their details would have to be set first using userSetDetails() which will first
    update all the variables.
     */
   public static ContentValues userInsertData(Activity act){
        ContentValues cv = new ContentValues();
        cv.put("user_first_name",getUserFirstName());
        cv.put("user_surname", getUserSurname());
        cv.put("user_login_username", getUserLoginUsername());
        cv.put("user_login_password",getUserLoginPassword());
        cv.put("user_phone_number",getUserPhoneNumber());
        //will remove convertBoolToInt later
        int convertBoolToInt = getUserLoginStaff()? 1:0;
        cv.put("user_login_staff",convertBoolToInt);

        //for now I'm just going to make the restaurant staff be assigned a random restaurant.
        if(getUserLoginStaff()){
            //cv.put("user_restaurant_id",getStaffRestaurantID());
            cv.put("user_restaurant_id",1);
        }
       return cv;
   }

   public static void registerUser(Activity act,String name, String surname, String username, String password, String phoneNumber, Boolean isStaff){
        setUserFirstName(name);
        setUserSurname(surname);
        setUserLoginUsername(username);
        setUserLoginPassword(password);
        setUserPhoneNumber(phoneNumber);
        setUserLoginStaff(isStaff);

        ContentValues cv = userInsertData(act);

       //inserts to STAFF_TBL
       if(getUserLoginStaff()){
          php.doRequest(act,"staff_insert",cv,null);
          return;
       }

       //inserts into CUSTOMER_TBL
       php.doRequest(act,"customer_insert",cv,null);
   }



    /*following is standard get and set methods. This is what you would use to request data
 around the program*/
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

package com.example.restaurant;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomerLayout extends LinearLayout {
TextView t, tCustID, tCustFName,tCustSurname,tcustPhone;
// CUSTOMER LAYOUT FILE FOR ADDING ENTRIES OF CUSTOMER INFO TO A VIEW FROM MYSQL DATABASE USING PHPSERVER
    public CustomerLayout(Context p){
        super(p) ;
        setOrientation(LinearLayout.HORIZONTAL);
        t = new TextView(p);
        tCustID= new TextView(p);
        tCustID.setX(10);
        tCustFName= new TextView(p);
        tCustFName.setX(140);
        tCustSurname= new TextView(p);
        tCustSurname.setX(250);
        tcustPhone= new TextView(p);
        tcustPhone.setX(300);
        addView(tCustID);
        addView(tCustFName);
        addView(tCustSurname);
        addView(tcustPhone);

    }
    public void populate(JSONObject jo) throws JSONException {
        String custID=jo.getString("CUSTOMER_ID");
        String custFName=jo.getString("CUSTOMER_FIRST_NAME");
        String custSurname=jo.getString("CUSTOMER_SURNAME");
        String custPhone=jo.getString("CUSTOMER_PHONE_NUMBER");

        tCustID.setText(custID);
        tCustFName.setText(custFName);
        tCustSurname.setText(custSurname);
        tcustPhone.setText(custPhone);

    }

}
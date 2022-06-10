package com.example.restaurant;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.internal.http1.HeadersReader;

public class ViewStaffOrderActivity extends Activity {
    private LinearLayout top1;
    private ScrollView sv;
    // ACTIVITY LISTING ALL ORDERS MADE BY STAFF
    public void processOrders(String json) throws JSONException {
        top1.removeAllViews();
        TextView heading= new TextView(this);
        heading.setText("orderID\t\tTime\t\t\t\t\t\t\t\t\tStatus\t\t\tRating\t\tCustomer");
        heading.setTextSize(15);
        top1.addView(heading);
        //POPULATES ORDERS
        JSONArray ja = new JSONArray(json);
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            ViewOrderLayout cl = new ViewOrderLayout(this);
            cl.populate(jo);
            if((i)%2==0){
                cl.setBackgroundColor(Color.parseColor("#2804b8"));
            }
            top1.addView(cl);
        }
    }



        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            top1 = new LinearLayout(this);
            top1.removeAllViews();
            top1.setOrientation(LinearLayout.VERTICAL);
            TextView header1 = new TextView(this);
            header1.setText("Orders");
            top1.addView(header1);

            Intent current = getIntent();
//            String staffID = current.getStringExtra("staffID");
            PHPRequest php = new PHPRequest();
            ContentValues cv = new ContentValues();
            cv.put("STAFF_ID", Integer.toString(User.getUserID())); //RETURNS ALL ORDERS MADE BY STAFF
            setContentView(top1);
            php.doRequest(this, "orders", cv, new RequestHandler() {
                @Override
                public void processResponse(String response) {
                    try {
                        processOrders(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            sv= new ScrollView(this); //ADDS SCROLLABLE TEXT TO VIEW ALL ENTRIES
            if (top1.getParent() != null){
                ((ViewGroup)top1.getParent()).removeView(top1);
            }
            sv.addView(top1);

            setContentView(sv);


        }
}
package com.example.restaurant;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Objects;

public class AddOrderActivity extends Activity {
    LinearLayout top, bottom;
    TextView t;

    private ScrollView sv;
// ADDORDER ACTIVITY TO ALLOW USER TO ADD AND INSERT NEW ORDER ENTRY IN MYSQL DATABASE USING PHP SERVER
    public void processCustomers(String json) throws JSONException {
        top.removeAllViews();
        TextView header = new TextView(this);
        TextView header2 = new TextView(this);
        TextView header3 = new TextView(this);
        header2.setText("ADD ORDER");
        header2.setX(225);
        header2.setTextSize(30);
        header3.setText("Tap on Customer to add order");
        header3.setX(200);
        top.addView(header2);
        top.addView(header3);
        header.setText("Customer ID\t First Name\t Surname\t Phone Number\t");

        top.addView(header);
        JSONArray ja = new JSONArray(json);
        for(int i =0; i<ja.length();i++){
            JSONObject jo = ja.getJSONObject(i);
            CustomerLayout cl = new CustomerLayout(this);
            cl.populate(jo);
            if(i%2==0){
                cl.setBackgroundColor(Color.parseColor("#2804b8"));
            }
            cl.tcustPhone.setX(300);
            cl.setOnClickListener(new View.OnClickListener() {// SENDS A REQUEST TO PHPSERVER TO INSERT NEW ENTRY INTO ORDER_TBL AND SENDS USER BACK TO STAFFACTIVITY
                @Override
                public void onClick(View view) {
                    PHPRequest PHP = new PHPRequest();
                    ContentValues cv = new ContentValues();
                    cv.put("STAFF_ID", Integer.toString(User.getUserID()));
                    try {
                        cv.put("CUSTOMER_ID",jo.getString("CUSTOMER_ID"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    PHP.doRequest(AddOrderActivity.this, "order_insert", cv, new RequestHandler() {
                        @Override
                        public void processResponse(String response) {
                                Toast.makeText(AddOrderActivity.this, "Added Order succesfully", Toast.LENGTH_SHORT).show();
                            Intent StaffActivity= new Intent( AddOrderActivity.this, com.example.restaurant.StaffActivity.class);
//                            StaffActivity.putExtra("staffID",staffID);
                            startActivity(StaffActivity);
                        }
                    });


                }
            });
            top.addView(cl);
        }

    }




    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        top = new LinearLayout(this);
        bottom = new LinearLayout(this);
        top.removeAllViews();
        top.setOrientation(LinearLayout.VERTICAL);
        Intent current = getIntent();
//        String staffID = current.getStringExtra("staffID");
        PHPRequest PHP = new PHPRequest();
        ContentValues cv = new ContentValues();

        PHP.doRequest(this, "customer", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                try{
                    processCustomers(response);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        sv= new ScrollView(this);
        if (top.getParent() != null){
            ((ViewGroup)top.getParent()).removeView(top);
        }
        sv.addView(top);

        setContentView(sv);




    }
}
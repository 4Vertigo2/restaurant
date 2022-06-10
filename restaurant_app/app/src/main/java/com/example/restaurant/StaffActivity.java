package com.example.restaurant;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StaffActivity extends AppCompatActivity {
Button viewOrder;
Button addOrder;
String staffID;
private static PHPRequest php = new PHPRequest();
private Button settingsBtn;
static public String average;
private ScrollView sv;
public LinearLayout top, bottom, orderList ,aver, averLevel;

//STAFF ACTIVITY USED BY USERS WHO ARE LOGGED IN AS STAFF
public void setAvgRating(String response){ // SETS TEXTVIEW WITH CALCULATED AVERAGE FROM PHP SERVER
    aver.removeAllViews();
    averLevel= new LinearLayout(StaffActivity.this);
    TextView averageT = new TextView(StaffActivity.this);
    average="";
    try {
        JSONArray ja = new JSONArray(response);
        for(int i =0; i<ja.length();i++){
            JSONObject jo = ja.getJSONObject(i);
            if (jo.getString("STAFF_ID").equals(Integer.toString(User.getUserID()))){
                average= jo.getString("AVG_RATING");
                if (average != null){
                    averageT.setText("Staff Average Rating: "+ Double.toString(Double.parseDouble(average) *100) + "%" );

                }else{
                    averageT.setText("Staff Average Rating: No rating");
                }

            }
        }
        aver.addView(averageT);
        top.addView(aver);
    } catch (JSONException e) {
        e.printStackTrace();
    }

}





public void getAvgRating(){ // GETS AVERAGE RATING FROM SERVER
        PHPRequest PHP = new PHPRequest();
        ContentValues cv = new ContentValues();
        cv.put("STAFF_ID",Integer.toString(User.getUserID()));
        PHP.doRequest(this, "getRating", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                setAvgRating(response);
            }
        });

    }

  public void activityInit(){ //INITIALIZES ACTIVITY WITH DEFAULT LAYOUT
      aver= new LinearLayout(StaffActivity.this);
/*
      PHPRequest php =new PHPRequest();
      ContentValues cv= new ContentValues();
      cv.put("LOGIN_ID","1");
      php.doRequest(this, "staff2", cv, new RequestHandler() {
          @Override
          public void processResponse(String response) {
              try {
                  getStaffID(response);
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
          });*/
        top= new LinearLayout(this);
        top.setOrientation(LinearLayout.VERTICAL);
        TextView header= new TextView(this);
        header.setText("STAFF");
        header.setTextSize(30);
        header.setX(280);

   getAvgRating();

        TextView naverageT = new TextView(this);

      naverageT.setText("Staff Average: "+average);


      top.addView(header);
      top.addView(aver);



        bottom= new LinearLayout(this);
        bottom.setOrientation(LinearLayout.HORIZONTAL);
        addOrder= new Button(this);
        viewOrder= new Button(this);
        settingsBtn = new Button(this);
        settingsBtn.setText("Settings");
        addOrder.setText("Add Order");
        addOrder.setPadding(70,1,70,1);
        viewOrder.setText("View Order");
        viewOrder.setPadding(70,1,70,1);
        viewOrder.setX(32);
//        settingsBtn.setPadding(100,1,100,1);
        settingsBtn.setX(60);
        settingsBtn.setPadding(70,1,70,1);
        bottom.addView(addOrder);
        bottom.addView(viewOrder);
        bottom.addView(settingsBtn);
        top.addView(bottom);


    }
    public void getStaffID(String json) throws JSONException { //GETS STAFF ID FROM SERVER
        JSONArray jaStaff = new JSONArray(json);
        JSONObject jo= jaStaff.getJSONObject(0);
        staffID= jo.getString("STAFF_ID");

    }

    public void processOrders(String json) throws JSONException { //POPULATES ACTIVITY WITH CURRENT ORDERS

        TextView heading= new TextView(this);
        heading.setText("orderID\torderTime\torderStatus\torderCustomer");
        orderList.addView(heading);
        JSONArray ja = new JSONArray(json);
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            if(Integer.parseInt(jo.getString("ORDER_STATUS")) <1){
                StaffCurrentLayout cl = new StaffCurrentLayout(this, StaffActivity.this,json);
                cl.populate(jo);

                orderList.addView(cl);
            }
        }





    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInit();
        Help.goToActivity(this, settingsBtn, new SettingsActivity());

        ContentValues cvName = new ContentValues();
        cvName.put("resID",User.getStaffRestaurantID());
        php.doRequest(this, "getRestaurant", cvName, new RequestHandler() {
            @Override
            public void processResponse(String response) {
               try {
                   JSONObject jo = new JSONObject(response);
                   String name = response.substring(response.indexOf(":") + 2, response.indexOf("}") - 1);
                   setTitle(name);
               }
               catch(JSONException e){
                   System.out.println("Restaurant name fail");
               }
            }
        });
        orderList= new LinearLayout(this);
        orderList.setOrientation(LinearLayout.VERTICAL);

        ContentValues cv = new ContentValues();

        cv.put("STAFF_ID", Integer.toString(User.getUserID()));
        php.doRequest(this, "orders", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                try {
                    processOrders(response); // POPULATES ACTIVITY WITH CURRENT ORDERS

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        viewOrder.setOnClickListener(new View.OnClickListener() {// SENDS USER TO VIEWORDERACTIVITY
            @Override
            public void onClick(View view) {
                Intent viewOrderActivity= new Intent(StaffActivity.this, ViewStaffOrderActivity.class);
//               viewOrderActivity.putExtra("staffID",User.getUserID());
                startActivity(viewOrderActivity);
            }
        });
        addOrder.setOnClickListener(new View.OnClickListener() { //SENDS USER TO ADDORDER ACTIVITY
            @Override
            public void onClick(View view) {
                Intent AddOrderActivity= new Intent(StaffActivity.this, AddOrderActivity.class);
//               AddOrderActivity.putExtra("staffID",User.getUserID());
                startActivity(AddOrderActivity);
            }
        });
        sv= new ScrollView(this);
      sv.addView(orderList);
        top.addView(sv);
      setContentView(top);
    }
}
package com.example.restaurant;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
// LAYOUT THAT ADDS CURRENT ORDERS BEING WORKED ON
public class StaffCurrentLayout extends LinearLayout {
    TextView t;
    String orderID;
    String orderStatus, orderTime, orderCustomer;
    public StaffCurrentLayout(Context p, Activity act, String response){
        super(p) ;
        setOrientation(LinearLayout.VERTICAL);
        t = new TextView(p);

        PHPRequest php= new PHPRequest();
        ContentValues cv = new ContentValues();

        LinearLayout vert= new LinearLayout(p);
        vert.setOrientation(LinearLayout.HORIZONTAL);
        Button update = new Button(p);
        update.setText("Update"); // USER CAN CLICK BUTTON TO UPDATE ORDER STATUS
        update.setX(80);
        update.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                update(act);
            }
        });

        vert.addView(t);
        vert.addView(update);
        addView(vert);

    }

    public void update(Activity p){ // INCREMENTS ORDER_STATUS BY 1 AND SENDS A REQUEST TO BE STORED ON MYSQL DATABASE USING PHP SERVER
        orderStatus= Integer.toString(Integer.parseInt(orderStatus)+1);
        PHPRequest php= new PHPRequest();
        ContentValues cv = new ContentValues();
        cv.put("ORDER_ID", orderID );
        cv.put("ORDER_STATUS", Integer.parseInt(orderStatus));
        php.doRequest(p, "order_update",cv,null);

        String orderStatusMessage="";
        if (Integer.parseInt(orderStatus) == -1){
            orderStatusMessage="Pending";
        }else if (Integer.parseInt(orderStatus) ==0){
            orderStatusMessage="Ready";
        }else{
            orderStatusMessage="Collected";
        }
        String  output= orderID+"\t"+orderTime+"\t"+orderStatusMessage+"\t\t\t\t"+orderCustomer;

        t.setText(output);
    }
    public void populate(JSONObject jo) throws JSONException { //POPULATES TEXTFIELD WITH  CURRENT ORDER DATA
        orderID= jo.getString("ORDER_ID");
        orderTime= jo.getString("ORDER_TIME");
        orderStatus=jo.getString("ORDER_STATUS");

        orderCustomer=jo.getString("CUSTOMER_ID");
        String orderStatusMessage="";
        if (Integer.parseInt(orderStatus) == -1){
            orderStatusMessage="Pending";
        }else if (Integer.parseInt(orderStatus) ==0){
            orderStatusMessage="Ready";
        }else{
            orderStatusMessage="Collected";
        }
        String  output= orderID+"\t"+orderTime+"\t"+orderStatusMessage+"\t\t\t\t"+orderCustomer;

        t.setText(output);
    }



}

package com.example.restaurant;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewOrderLayout extends  LinearLayout {
    TextView ViewOrdert;
    TextView orderIDT,orderTimeT,orderStatusT,orderRatingT,orderCustomerT;
    LinearLayout ViewOrderHorizontal;
    // LAYOUT TO ADD INDIVIDUAL ENTRIES OF ORDERS FOR VIEWORDERACTIVITY
    public ViewOrderLayout(Context p){
        super(p);

        setOrientation(LinearLayout.HORIZONTAL);

        orderIDT = new TextView(p);
        orderIDT.setPadding(0,1,10,1);

        orderTimeT= new TextView(p);
        orderTimeT.setPadding(30,1,0,1);

        orderStatusT= new TextView(p);
        orderStatusT.setPadding(60,1,0,1);
        orderRatingT= new TextView(p);
        orderRatingT.setPadding(30,1,0,1);
        orderCustomerT = new TextView(p);
        orderCustomerT.setPadding(20,1,0,1);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        addView(orderIDT,lp);

        addView(orderTimeT,lp);
        addView(orderStatusT,lp);
        addView(orderRatingT,lp);
        addView(orderCustomerT,lp);

    }
    //POPULATES TEXTVIEWS OF DATA FROM MYSQL DATABASE USING PHP SERVER
    public void populate(JSONObject jo) throws JSONException {
        String orderStatusMessage="";
        String orderRatingMessage="";
         String orderID= jo.getString("ORDER_ID");
         String orderTime= jo.getString("ORDER_TIME");
         String orderStatus=jo.getString("ORDER_STATUS");
         String orderRating=jo.getString("ORDER_RATING");
         String orderCustomer=jo.getString("CUSTOMER_ID");
        if (Integer.parseInt(orderStatus) == -1){
            orderStatusMessage="Pending";
        }else if (Integer.parseInt(orderStatus) ==0){
            orderStatusMessage="Ready";
        }else{
            orderStatusMessage="Collected";
        }
        if (Double.parseDouble(orderRating) == 0.5){
            orderRatingMessage="No Rating";
        }else if (Double.parseDouble(orderRating) ==0){
            orderRatingMessage="Thumbs Down";
        }else{
            orderRatingMessage="Thumbs Up";
        }

        String  output= orderID+"\t"+orderTime+"\t\t\t\t"+orderStatus+"\t\t\t\t\t"+orderRating+"\t\t\t\t\t"+orderCustomer;
        orderIDT.setText(orderID);
        orderTimeT.setText(orderTime);
        orderStatusT.setText(orderStatusMessage);
        orderRatingT.setText(orderRatingMessage);
        orderCustomerT.setText(orderCustomer);
    }

}


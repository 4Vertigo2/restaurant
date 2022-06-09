package com.example.restaurant;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomerRequest extends LinearLayout {
    TextView id ;
    int order_id;
    double order_rating = 0;
    TextView status;
    TextView restaurant;
    TextView time;
    Help helper = new Help();
    PHPRequest php = new PHPRequest();
    ToggleButton tglBtnThumbsUp;
    ToggleButton tglBtnThumbsDown;

    public CustomerRequest(Context c, Activity act){
        super(c);
        setOrientation(LinearLayout.HORIZONTAL);
        id = new TextView(c);
        status = new TextView(c);
        restaurant = new TextView(c);
        time = new TextView(c);
        tglBtnThumbsUp = new ToggleButton(c);
        tglBtnThumbsUp.setText("↑");
        tglBtnThumbsDown = new ToggleButton(c);
        tglBtnThumbsDown.setText("↓");
        ContentValues cv = new ContentValues();



        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.weight = 0;
        id.setPadding(0,0,20,0);
        addView(id,lp);
            tglBtnThumbsUp.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    tglBtnThumbsDown.setChecked(false);
                    tglBtnThumbsUp.setChecked(true);
                    tglBtnThumbsUp.setText("↑");
                    tglBtnThumbsDown.setText("↓");
                    cv.put("rating", 1);
                    cv.put("id", order_id);
                    php.doRequest(act, "customer_rating", cv, null);

                }
            });
            tglBtnThumbsDown.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    tglBtnThumbsUp.setChecked(false);
                    tglBtnThumbsDown.setChecked(true);
                    tglBtnThumbsUp.setText("↑");
                    tglBtnThumbsDown.setText("↓");
                    cv.put("rating", 0);
                    cv.put("id", order_id);
                    php.doRequest(act, "customer_rating", cv, null);
                }
            });


        LinearLayout right = new LinearLayout(c);
        right.setOrientation(LinearLayout.VERTICAL);
        time.setPadding(0,0,0,10);
        right.addView(status);
        right.addView(restaurant);
        right.addView(time);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp2.weight = 1;
        addView(right, lp2);

    }

    public void populate(JSONObject jo){
        try {
            id.setText("#" + jo.getString("ORDER_ID"));
            order_rating = jo.getDouble("ORDER_RATING");
            if(order_rating == 0.5){
                addView(tglBtnThumbsUp);
                addView(tglBtnThumbsDown);
            }
            order_id = Integer.parseInt(jo.getString("ORDER_ID"));
            status.setText(helper.convertCodeToStatus(jo.getInt("ORDER_STATUS")));
            restaurant.setText(jo.getString("RESTAURANT_NAME"));
            time.setText(jo.getString("ORDER_TIME"));

        }
        catch(JSONException e){
            System.out.println("Order Class : JSON failed");
        }

    }

}

package com.example.restaurant;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomerRequest extends LinearLayout {
    TextView id ;
    TextView status;
    TextView restaurant;
    TextView time;
    Help helper = new Help();
    public CustomerRequest(Context c){
        super(c);
        setOrientation(LinearLayout.HORIZONTAL);
        id = new TextView(c);
        status = new TextView(c);
        restaurant = new TextView(c);
        time = new TextView(c);
        ToggleButton tglBtnThumbsUp;
        ToggleButton tglBtnThumbsDown;

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.weight = 0;
        id.setPadding(0,0,20,0);
        addView(id,lp);

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
            status.setText(helper.convertCodeToStatus(jo.getInt("ORDER_STATUS")));
            restaurant.setText(jo.getString("RESTAURANT_NAME"));
            time.setText(jo.getString("ORDER_TIME"));
        }
        catch(JSONException e){
            System.out.println("Order Class : JSON failed");
        }

    }
}

package com.example.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class HistoryActivity extends Activity {
    //class for the history page of customer's orders
    //optimise add views by extending CustomerOrderActivity (wip) [ ]
    private LinearLayout orderHistoryContent;
    private TextView orderHistoryTxf;/*, restaurantNameTxf, orderDateTxf;*/
    private CardView orderCard;
    private Button orderViewBtn, settingsBtn;
    private ScrollView sv;

    //Php requests
    OkHttpClient client = new OkHttpClient();
    private static PHPRequest php = new PHPRequest();

    private void OrderAddViews(){
        //adds views to linearlayout
//        orderCard.addView(orderHistoryTxf);
//        orderHistoryContent.addView(orderHistoryTxf);
//        orderHistoryContent.addView(restaurantNameTxf);
//        orderHistoryContent.addView(orderDateTxf);
//        orderHistoryContent.addView(orderCard);
        orderHistoryContent.addView(settingsBtn);
        orderHistoryContent.addView(orderViewBtn);
    }

    public void activityInit(){

        //initialises views
        orderHistoryContent = new LinearLayout(this);
        //old textviews for setting each field individually
//        statusTxf = new TextView(this);
//        restaurantNameTxf = new TextView(this);
//        orderTimeTxf = new TextView(this);
        orderViewBtn = new Button(this);
        settingsBtn = new Button(this);

        //sets views
        orderHistoryContent.setOrientation(LinearLayout.VERTICAL);
        orderViewBtn.setText("Current Orders");
        settingsBtn.setText("Settings");

        OrderAddViews();
        setContentView(orderHistoryContent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInit();
        Help.goToActivity(this, orderViewBtn, new CustomerOrderActivity());
        Help.goToActivity(this, settingsBtn, new SettingsActivity());
        ContentValues cv = new ContentValues();
        cv.put("customer_id", User.getUserID());
        cv.put("status", 2);

        php.doRequest(HistoryActivity.this, "customer_order", cv, response ->  {
            try {
                Help help = new Help();
                JSONArray jArr = new JSONArray(response);
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject jObj = jArr.getJSONObject(i);
                    CustomerRequest cr = new CustomerRequest(this, HistoryActivity.this);
                    cr.populate(jObj);
                    orderHistoryContent.addView(cr);

                }
            }
            catch (JSONException e) {
                System.out.println("Order Class : JSON failed");
            }
        });
        sv = new ScrollView(this);
        if (orderHistoryContent.getParent() != null){
            ((ViewGroup)orderHistoryContent.getParent()).removeView(orderHistoryContent);
        }
        sv.addView(orderHistoryContent);
        setContentView(sv);
    }
}

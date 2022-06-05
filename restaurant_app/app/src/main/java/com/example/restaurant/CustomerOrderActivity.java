package com.example.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
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
import org.w3c.dom.Text;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CustomerOrderActivity extends Activity {
    //declare views and layout
    private LinearLayout viewOrderContent;
    private TextView orderCurrTxf;/*, statusTxf, restaurantNameTxf, orderTimeTxf*/
    private CardView orderCard;
    private Button orderHistoryBtn;
    private ScrollView sv;
    //Php requests
    OkHttpClient client = new OkHttpClient();
    private static PHPRequest php = new PHPRequest();

    private void OrderAddViews(){
        //adds views to linearlayout
//        orderCard.addView(orderCurrTxf);
//        orderCard.addView(statusTxf);
//        orderCard.addView(restaurantNameTxf);
//        orderCard.addView(orderTimeTxf);
        viewOrderContent.addView(orderHistoryBtn);
//        viewOrderContent.addView(orderCard);
    }

    public void activityInit(){

        //initialises views
        viewOrderContent = new LinearLayout(this);
//        orderCard = new CardView(this);
//        orderCurrTxf = new TextView(this);
        //old textviews for setting each field individually
//        statusTxf = new TextView(this);
//        restaurantNameTxf = new TextView(this);
//        orderTimeTxf = new TextView(this);
        orderHistoryBtn = new Button(this);

        //sets views
        viewOrderContent.setOrientation(LinearLayout.VERTICAL);
        orderHistoryBtn.setText("History");

        OrderAddViews();
        setContentView(viewOrderContent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = new User();
//        activityInit(user.getUserID());
        activityInit();
        Help.goToActivity(this, orderHistoryBtn, new HistoryActivity());


        ContentValues cv = new ContentValues();
        cv.put("customer_id", user.getUserID());
        cv.put("status", 0);

        php.doRequest(CustomerOrderActivity.this, "customer_order", cv, response ->  {
                try {
                    Help help = new Help();
                    JSONArray jArr = new JSONArray(response);
                    for (int i = 0; i < jArr.length(); i++) {
                        JSONObject jObj = jArr.getJSONObject(i);
                        CustomerRequest cr = new CustomerRequest(this, CustomerOrderActivity.this);
                        cr.populate(jObj);
                        viewOrderContent.addView(cr);

                    }
                }
                catch (JSONException e) {
                    System.out.println("Order Class : JSON failed");
                }
        });
        sv = new ScrollView(this);
        if (viewOrderContent.getParent() != null){
            ((ViewGroup)viewOrderContent.getParent()).removeView(viewOrderContent);
        }
        sv.addView(viewOrderContent);
        setContentView(sv);
    }
}
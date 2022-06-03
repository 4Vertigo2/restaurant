package com.example.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CustomerOrderActivity extends Activity {
    //declare views and layout
    private LinearLayout viewOrderContent;
    private TextView orderCurrTxf;/*, statusTxf, restaurantNameTxf, orderTimeTxf*/
    private CardView orderCard;
    private Button orderHistoryBtn;
    private Activity act;
    //Php requests
    OkHttpClient client = new OkHttpClient();
    private static PHPRequest php = new PHPRequest();

    private void OrderAddViews(){
        //adds views to linearlayout
        orderCard.addView(orderCurrTxf);
//        orderCard.addView(statusTxf);
//        orderCard.addView(restaurantNameTxf);
//        orderCard.addView(orderTimeTxf);
        viewOrderContent.addView(orderHistoryBtn);
        viewOrderContent.addView(orderCard);
    }

    public void activityInit(int customerID){
        ContentValues cv = new ContentValues();
        cv.put("customer_id", customerID);
        cv.put("status", 0);
        php.doRequest(act, "customer_order", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                try {
                    Help help = new Help();
                    JSONArray jArr = new JSONArray(response);
                    for (int i = 0; i < jArr.length(); i++) {
                        JSONObject jObj = jArr.getJSONObject(i);
                        int orderID = jObj.getInt("ORDER_ID");
                        String orderStatus = help.convertCodeToStatus(jObj.getInt("ORDER_STATUS"));
                        String restaurant = jObj.getString("RESTAURANT_NAME");
                        String orderTime = jObj.getString("ORDER_TIME");
                        String str = orderID + "\n" + orderStatus + "\n" +
                                restaurant + "\n" + orderTime + "\n";
                    }
                }
                catch (JSONException e) {
                    System.out.println("Order Class : JSON failed");
                }
            }
        });
        // CustomerRequest window feature action bar
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        // Set the CardView layoutParams
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        //initialises views
        viewOrderContent = new LinearLayout(this);
        orderCard = new CardView(this);
        orderCurrTxf = new TextView(this);
        //old textviews for setting each field individually
//        statusTxf = new TextView(this);
//        restaurantNameTxf = new TextView(this);
//        orderTimeTxf = new TextView(this);
        orderHistoryBtn = new Button(this);

        //sets views
        viewOrderContent.setOrientation(LinearLayout.VERTICAL);
        orderCard.setLayoutParams(params);
        orderCard.setRadius(9);
        orderCard.setContentPadding(15,15,870,15);
        orderCard.setCardBackgroundColor(Color.parseColor("#D3D3D3"));
        orderCard.setMaxCardElevation(15);
        orderCard.setCardElevation(9);
//        orderIdTxf.setHint("#0000");
//        statusTxf.setHint("Default");
//        restaurantNameTxf.setHint("Restaurant");
//        orderTimeTxf.setHint("00:00");
        orderHistoryBtn.setText("History");

        OrderAddViews();
        setContentView(viewOrderContent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = new User();
        activityInit(user.getUserID());
        Help.goToActivity(this, orderHistoryBtn, new HistoryActivity());

    }
}
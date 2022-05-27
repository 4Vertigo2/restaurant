package com.example.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomerOrderActivity extends Activity {
    //declare views and layout
    private LinearLayout viewOrderContent;
    private TextView orderCurrTxf;/*, statusTxf, restaurantNameTxf, orderTimeTxf*/
    private CardView orderCard;
    Button orderHistoryBtn;

    private void OrderAddViews(){
        //adds views to linearlayout
        orderCard.addView(orderCurrTxf);
//        orderCard.addView(statusTxf);
//        orderCard.addView(restaurantNameTxf);
//        orderCard.addView(orderTimeTxf);
        viewOrderContent.addView(orderHistoryBtn);
        viewOrderContent.addView(orderCard);
    }

    public void activityInit(){
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
        orderCurrTxf.setHint("#0000\nDefault\nRestaurant\n00:00");
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
        activityInit();
        Help.goToActivity(this, orderHistoryBtn,new HistoryActivity());
    }
}
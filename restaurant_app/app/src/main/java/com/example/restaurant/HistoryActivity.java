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

public class HistoryActivity extends Activity {
    //class for the history page of customer's orders
    //optimise add views by extending CustomerOrderActivity (wip) [ ]
    private LinearLayout orderHistoryContent;
    private TextView orderHistoryTxf;/*, restaurantNameTxf, orderDateTxf;*/
    private CardView orderCard;
    private Button orderViewBtn;

    private void OrderAddViews(){
        //adds views to linearlayout
        orderCard.addView(orderHistoryTxf);
//        orderHistoryContent.addView(orderHistoryTxf);
//        orderHistoryContent.addView(restaurantNameTxf);
//        orderHistoryContent.addView(orderDateTxf);
        orderHistoryContent.addView(orderCard);
        orderHistoryContent.addView(orderViewBtn);
    }

    public void activityInit(){
        // CustomerRequest window feature action bar
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        // Set the CardView layoutParams
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        //initialises views
        orderHistoryContent = new LinearLayout(this);
        orderCard = new CardView(this);
        orderHistoryTxf = new TextView(this);
        //old textviews for setting each field individually
//        statusTxf = new TextView(this);
//        restaurantNameTxf = new TextView(this);
//        orderTimeTxf = new TextView(this);
        orderViewBtn = new Button(this);

        //sets views
        orderHistoryContent.setOrientation(LinearLayout.VERTICAL);
        orderCard.setLayoutParams(params);
        orderCard.setRadius(9);
        orderCard.setContentPadding(15,15,870,15);
        orderCard.setCardBackgroundColor(Color.parseColor("#D3D3D3"));
        orderCard.setMaxCardElevation(15);
        orderCard.setCardElevation(9);
        orderHistoryTxf.setHint("#0000\nRestaurant\n2022/01/01");
//        orderHistoryTxf.setHint("#0000");
//        restaurantNameTxf.setHint("Restaurant");
//        orderDateTxf.setHint("2022/01/01");
//        orderViewBtn.setText("History");
        orderViewBtn.setText("Current Orders");

        OrderAddViews();
        setContentView(orderHistoryContent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInit();
        Help.goToActivity(this, orderViewBtn, new CustomerOrderActivity());
    }
}
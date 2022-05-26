package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderHistoryActivity extends AppCompatActivity {
    private LinearLayout orderHistoryContent;
    private TextView orderIdTxf, restaurantNameTxf, orderDateTxf;
    Button orderViewBtn;

    private void OrderHistoryViews(){
        //adds views to linearlayout
        orderHistoryContent.addView(orderIdTxf);
        orderHistoryContent.addView(restaurantNameTxf);
        orderHistoryContent.addView(orderDateTxf);
        orderHistoryContent.addView(orderViewBtn);
    }

    public void activityInit(){
        //initialises views
        orderHistoryContent = new LinearLayout(this);
        orderIdTxf = new TextView(this);
        restaurantNameTxf = new TextView(this);
        orderDateTxf = new TextView(this);
        orderViewBtn = new Button(this);

        //sets views
        orderHistoryContent.setOrientation(LinearLayout.VERTICAL);
        orderIdTxf.setHint("#0000");
        restaurantNameTxf.setHint("Restaurant");
        orderDateTxf.setHint("2022/01/01");
        orderViewBtn.setText("History");

        OrderHistoryViews();
        setContentView(orderHistoryContent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInit();
        Help.goToActivity(this, orderViewBtn, new ViewOrderActivity());
    }
}
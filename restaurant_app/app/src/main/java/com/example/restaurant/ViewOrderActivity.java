package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewOrderActivity extends AppCompatActivity {
    //declare views and layout
    private LinearLayout viewOrderContent;
    private TextView orderIdTxf, statusTxf, restaurantNameTxf, orderTimeTxf;
    Button orderHistoryBtn;

    private void OrderAddViews(){
        //adds views to linearlayout
        viewOrderContent.addView(orderIdTxf);
        viewOrderContent.addView(statusTxf);
        viewOrderContent.addView(restaurantNameTxf);
        viewOrderContent.addView(orderTimeTxf);
        viewOrderContent.addView(orderHistoryBtn);
    }

    public void activityInit(){
        //initialises views
        viewOrderContent = new LinearLayout(this);
        orderIdTxf = new TextView(this);
        statusTxf = new TextView(this);
        restaurantNameTxf = new TextView(this);
        orderTimeTxf = new TextView(this);
        orderHistoryBtn = new Button(this);

        //sets views
        viewOrderContent.setOrientation(LinearLayout.VERTICAL);
        orderIdTxf.setHint("#0000");
        statusTxf.setHint("Default");
        restaurantNameTxf.setHint("Restaurant");
        orderTimeTxf.setHint("00:00");
        orderHistoryBtn.setText("History");

        OrderAddViews();
        setContentView(viewOrderContent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInit();
        Help.goToActivity(this, orderHistoryBtn,new OrderHistoryActivity());
    }
}
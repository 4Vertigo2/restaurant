package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout l = new LinearLayout(this);
        l.setOrientation(l.VERTICAL);
        setContentView(l);
        TextView t = new TextView(this);

        l.addView(t);
        PHPRequest php =new PHPRequest();
        ContentValues cv= new ContentValues();
        php.doRequest(this, "cars", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                    t.setText(response);
            }
        });
    }
}
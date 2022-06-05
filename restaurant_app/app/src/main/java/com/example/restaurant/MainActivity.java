package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.restaurant_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        ImageView gifView = new ImageView(this);
        LinearLayout l = new LinearLayout(this);

        String uri = "@drawable/takes";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);


        /*from raw folder*/
        Glide.with(this)
                .load("@drawable/pizza_loader_bg")
                .into(gifView);

        l.setOrientation(l.VERTICAL);
        LinearLayout gif = new LinearLayout(this);
        gif.setOrientation(LinearLayout.HORIZONTAL);
        l.addView(imageView);
        l.addView(gifView);
        setContentView(l);

//        TextView t = new TextView(this);
//
//        l.addView(t);
//        PHPRequest php =new PHPRequest();
//        ContentValues cv= new ContentValues();
//        php.doRequest(this, "cars", cv, new RequestHandler() {
//            @Override
//            public void processResponse(String response) {
//                    t.setText(response);
//            }
//        });
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 5000);

        //switches activity when app launches to Activity
        //used to test an Activity if you cannot run the Activity independently


    }
}
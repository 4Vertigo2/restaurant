package com.example.restaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;

public class Help {

    private static void transfer(Context context,Activity activity){
        Intent intent =new Intent(context, activity.getClass());
        context.startActivity(intent);
    }

    public static void goToActivity(Context context,Button btn, Activity act){
        btn.setOnClickListener(v -> transfer(context,act));
    }
}

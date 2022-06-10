package com.example.restaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Help {
//helper method that takes in the current activity (context) and the new activity
//it then calls startActivity to start a new instance of the Intent
    private static void transfer(Context context,Activity activity){
        Intent intent =new Intent(context, activity.getClass());
        context.startActivity(intent);
    }
//helper method that takes in (context)the current activity, the button that the user will press 
//and then calls the transfer method to go to the new activity
    public static void goToActivity(Context context,Button btn, Activity act){
        btn.setOnClickListener(v -> transfer(context,act));
    }

    //method to convert status codes to statuses
    public String convertCodeToStatus(int status){
        if (status == 0)
            return "Ready";
        if (status == 1)
            return "Collected";
        else
            return "Pending";
    }

    public static void enterKey(EditText txf){
        txf.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event){
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            if(txf == txf){
                                (txf).requestFocus();
                            }
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }
}

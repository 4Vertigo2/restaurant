package com.example.restaurant;

import android.app.Activity;
import android.content.ContentValues;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
Class used for Internet requests
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Required Constructors:
PHPRequest rq = new PHPRequest();
ContentValues cv= new ContentValues();
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
rq.doRequest(this, phpfilename, cv, rh)
this- sets Activity to current activity such as MainActivity
phpfilename- name of file no need for .php
cv- sets the query parameters for search
rh- function to use the response data. Can create another function elsewhere and call within RequestHandler
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Implementation Example in any Activity:
1. No Queries:
        PHPRequest php =new PHPRequest();
        ContentValues cv= new ContentValues();
        php.doRequest(this, "cars", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                    t.setText(response);
                    //processJson(response) example of calling another function
            }
        });
2. With Queries
        PHPRequest php =new PHPRequest();
        ContentValues cv= new ContentValues();
        cv.put("brand","Toyota");
        php.doRequest(this, "cars2", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                    t.setText(response);
            }
        });




 */
public class PHPRequest {
    OkHttpClient client = new OkHttpClient();

    public void doRequest(Activity a, String phpfile, ContentValues params, RequestHandler rh){
        FormBody.Builder builder = new FormBody.Builder();
        for(String key:params.keySet()){
            builder.add(key,params.getAsString(key));
        }

        Request request = new Request.Builder()
               .url("https://lamp.ms.wits.ac.za/~s2439564/"+phpfile+".php")
               .post(builder.build())
               .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData= response.body().string();

                a.runOnUiThread(new Runnable() {
                    @Override

                    public void run() {
                        try {

                            rh.processResponse(responseData);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }
        });

    }


}

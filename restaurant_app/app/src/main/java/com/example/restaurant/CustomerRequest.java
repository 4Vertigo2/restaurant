package com.example.restaurant;

public class CustomerRequest {
    private int status;

    public CustomerRequest(int inStatus){
        status = -1; //Pending is default (-1), ready is 0, collected is 1
    }

    public int getStatus(){
        return status;
    }
}

package com.xyzmastercrd.www;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    public static final String mycardata = "mycardetail";
    public static final String Firstnamp = "firname";
    public static final String Lasnamep = "lasname";
    public static final String Cardnop = "carno";
    public static final String Cvvnop = "cvvno";
    public static final String dummymoney = "dummymoney";
    public static final String dummycolpoint = "dummycollection";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedpreferences = getSharedPreferences(mycardata, Context.MODE_PRIVATE);


        getSupportActionBar().hide();

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    String value = sharedpreferences.getString(Cardnop, "");
                    if (value == "" ) {

                        Intent intent = new Intent(splash.this,MainActivity.class);
                        startActivity(intent);
                    }else if(value == "carno"){

                        Intent intent = new Intent(splash.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent i = new Intent (splash.this, home.class);
                        startActivity(i);
                    }

                }
            }
        };
        timerThread.start();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}

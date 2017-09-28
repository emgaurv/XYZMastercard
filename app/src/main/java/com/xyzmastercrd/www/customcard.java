package com.xyzmastercrd.www;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class customcard extends AppCompatActivity {
    public static final String mycardata = "mycardetail";
    public static final String dummycolpoint = "dummycollection";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customcard);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Custom cards");

        sharedpreferences = getSharedPreferences(mycardata, Context.MODE_PRIVATE);
        sharedpreferences = getSharedPreferences(mycardata, Context.MODE_PRIVATE);
        int money = sharedpreferences.getInt(dummycolpoint, 1);


        ImageView claim = (ImageView)findViewById(R.id.claim);
        TextView point = (TextView)findViewById(R.id.points);
        point.setText(String.valueOf(money));

        claim.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Sorry! this service is not available now!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

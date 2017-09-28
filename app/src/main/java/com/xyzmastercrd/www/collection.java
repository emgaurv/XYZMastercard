package com.xyzmastercrd.www;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class collection extends AppCompatActivity {
    public static final String mycardata = "mycardetail";
    public static final String dummycolpoint = "dummycollection";

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        sharedpreferences = getSharedPreferences(mycardata, Context.MODE_PRIVATE);
        sharedpreferences = getSharedPreferences(mycardata, Context.MODE_PRIVATE);
        int money = sharedpreferences.getInt(dummycolpoint, 1);
        ImageView apply = (ImageView) findViewById(R.id.apply);
       apply.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(collection.this,transaction.class);
               startActivity(i);
           }
       });
        TextView point = (TextView)findViewById(R.id.points);
        point.setText(String.valueOf(money));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(collection.this, moresmart.class);
                startActivity(inte);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

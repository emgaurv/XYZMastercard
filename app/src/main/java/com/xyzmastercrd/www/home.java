package com.xyzmastercrd.www;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {
    public static final String mycardata = "mycardetail";
    public static final String dummycolpoint = "dummycollection";
    public static final String Firstnamp = "firname";
    public static final String dummymoney = "dummymoney";


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         TextView balance = (TextView)findViewById(R.id.balance);
        ImageView crdiv = (ImageView)findViewById(R.id.cardicon);
        ImageView col = (ImageView)findViewById(R.id.collection);
        ImageView maps = (ImageView)findViewById(R.id.findus);

        ImageView sendd = (ImageView)findViewById(R.id.sendd);
        sendd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,sendata.class);
                startActivity(i);
            }
        });

        ImageView alarm = (ImageView)findViewById(R.id.alarm);
        alarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,payreminder.class);
                startActivity(i);
            }
        });

        ImageView graph = (ImageView)findViewById(R.id.graph);

        graph.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,graph.class);
                startActivity(i);
            }
        });

        final ImageView transaction = (ImageView)findViewById(R.id.transaction);
        transaction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,transaction.class);
                startActivity(i);
            }
        });


        sharedpreferences = getSharedPreferences(mycardata, Context.MODE_PRIVATE);
        int money = sharedpreferences.getInt(dummymoney, 1);
        balance.setText(String.valueOf(money));
                if (sharedpreferences.contains(Firstnamp)) {
            TextView nme = (TextView)findViewById(R.id.name);
            nme.setText("Howdy! " +sharedpreferences.getString(Firstnamp, ""));
        }

        crdiv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,cardata.class);
                startActivity(i);
            }
        });

        col.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,collection.class);
                startActivity(i);
            }
        });

        maps.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,neaybyoffice.class);
                startActivity(i);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(home.this, moresmart.class);
                startActivity(inte);
            }
        });
    }


}

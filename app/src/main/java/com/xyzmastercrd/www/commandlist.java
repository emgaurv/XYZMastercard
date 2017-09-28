package com.xyzmastercrd.www;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class commandlist extends AppCompatActivity {
    String[] countries = new String[] {
            "My current balance",
            "Check Points",
            "Tell me my card number",
            "My last Transaction",
            "show expenses statistics",
            "My Card Details",
            "Offices near by me",
            "Set a payment Reminder",
            "More commands"


    };
    int[] flags = new int[]{
            R.drawable.balance,
            R.drawable.points,
            R.drawable.cardnumber,
            R.drawable.transactions,
           R.drawable.statistics,
            R.drawable.carddetails,
            R.drawable.offices,
            R.drawable.reminder,
                R.drawable.mic
    };

    String[] currency = new String[]{
            "It will tell your current balance",
            "Check you collection points",
            "Know about your card number",
            "Get details about your last transaction",
            "Shows your expenses Graph",
            "Get all you card Details",
            "Area Offices Details",
            "sets payment reminder",
            "coming soon"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent inte = new Intent(commandlist.this,moresmart.class);
                startActivity(inte);

            }
        });


        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<9;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt",countries[i]);
            hm.put("cur",currency[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }

        String[] from = { "flag","txt","cur" };

        int[] to = { R.id.flag,R.id.txt,R.id.cur};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listviewitem, from, to);

        ListView listView = ( ListView ) findViewById(R.id.list_view);

        listView.setAdapter(adapter);

    }}
package com.xyzmastercrd.www;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class cardata extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String Cardnop = "carno";
    public static final String mycardata = "mycardetail";
    public static final String Firstnamp = "firname";
    public static final String Lasnamep = "lasname";

    public static final String addressp = "add";
    public static final String dateofbithp = "dob";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardata);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Card details");
        TextView cardno = (TextView)findViewById(R.id.cardnumber);
        TextView username = (TextView)findViewById(R.id.username);

        TextView cardnod = (TextView)findViewById(R.id.cardnodata);
        TextView holdernamed = (TextView)findViewById(R.id.holdername);
        TextView addressd = (TextView)findViewById(R.id.address);
        TextView dobd = (TextView)findViewById(R.id.dateofb);



        ImageView boared = (ImageView)findViewById(R.id.bored);
        boared.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(cardata.this , customcard.class);
                startActivity(i);
            }
        });


        ImageView lost = (ImageView)findViewById(R.id.lost);
        lost.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
          //  Dialogue

                AlertDialog.Builder builder1 = new AlertDialog.Builder(cardata.this);
                builder1.setMessage("Call Us to Block Your Card!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Call Us",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String phone = "34666777888";
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                                startActivity(intent);
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

        sharedpreferences = getSharedPreferences(mycardata, Context.MODE_PRIVATE);

        String cardn = sharedpreferences.getString(Cardnop, "");

        String addn = sharedpreferences.getString(addressp, "");
        String dobn = sharedpreferences.getString(dateofbithp, "");
        addressd.setText(addn);
        dobd.setText(dobn);


        String address = sharedpreferences.getString(addressp, "");
        String dob = sharedpreferences.getString(dateofbithp, "");
        addressd.setText(address);
        dobd.setText(dob);

        String f4 = cardn.substring(0, 4);
        String s4 = cardn.substring(4, 8);
        String t4 = cardn.substring(8, 12);
        String f_4 = cardn.substring(12, cardn.length());
        cardno.setText(f4 + "  " + s4 + "  " + t4+ "  " +f_4);
        cardnod.setText(f4 + "  " + s4 + "  " + t4+ "  " +f_4);
        String fn = sharedpreferences.getString(Firstnamp, "");
        String ln = sharedpreferences.getString(Lasnamep, "");
        username.setText(fn +" "+ ln);

        holdernamed.setText(fn +" "+ ln);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

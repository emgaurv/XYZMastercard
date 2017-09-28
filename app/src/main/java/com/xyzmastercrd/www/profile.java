package com.xyzmastercrd.www;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class profile extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    public static final String mycardata = "mycardetail";
    public static final String Firstnamp = "firname";
    public static final String Lasnamep = "lasname";
    public static final String addressp = "add";
    public static final String dateofbithp = "dob";
    public static final String Cardnop = "carno";
    public static final String Cvvnop = "cvvno";
    EditText address,dateofbirth;
    Button submit;
    Button skip;
    String add,dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        address = (EditText)findViewById(R.id.address);
        dateofbirth = (EditText)findViewById(R.id.dob);
        TextView fname = (TextView)findViewById(R.id.fname);
        TextView lname = (TextView)findViewById(R.id.lname);
        submit = (Button)findViewById(R.id.submit);

        sharedpreferences = getSharedPreferences(mycardata,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Firstnamp)) {
            fname.setText(sharedpreferences.getString(Firstnamp, ""));
       }
      if (sharedpreferences.contains(Lasnamep)) {
            lname.setText(sharedpreferences.getString(Lasnamep, ""));

       }

        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

              add = address.getText().toString();
              dob = dateofbirth.getText().toString();

                if(add.isEmpty() || dob.isEmpty()){

                }else{
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(addressp, add);
                    editor.putString(dateofbithp, dob);
                    editor.commit();

                    Intent i = new Intent (profile.this, home.class);
                    startActivity(i);

                }
            }
        });
        skip = (Button)findViewById(R.id.skip);
        skip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (profile.this, home.class);
                startActivity(i);
            }
        });
    }





}

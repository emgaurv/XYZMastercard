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


public class MainActivity extends AppCompatActivity {

    private EditText firstname,lastname,cardno,cvv;
    private String fname,lname,crdno,cvvno;
    private TextView err;
    private  Button go;
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
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(mycardata, Context.MODE_PRIVATE);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        cardno = (EditText) findViewById(R.id.cardno);
        cvv = (EditText) findViewById(R.id.cvv);
        go = (Button) findViewById(R.id.gologin);
         err = (TextView)findViewById(R.id.error);


       String value = sharedpreferences.getString(Cardnop, "");
        if (value == "" ) {
            clickaction();
        }else if(value == "carno"){
            clickaction();
        }
        else {
            Intent i = new Intent (MainActivity.this, home.class);
            startActivity(i);
        }

    }
    public void clickaction(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(dummymoney, 5658);
        editor.putInt(dummycolpoint, 15);
        editor.commit();

        go.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = firstname.getText().toString();
                lname = lastname.getText().toString();
                crdno = cardno.getText().toString();
                cvvno = cvv.getText().toString();

                if(fname.isEmpty() || lname.isEmpty()){
                    err.setText("Its look like you forgot Something!");
                }
                else if (crdno.length() != 16){
                    err.setText("Card No. Should have 16 digits");
                }
                else if (cvvno.length() < 3){
                    err.setText("Incorrect CVV");
                }else{
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Firstnamp, fname);
                    editor.putString(Lasnamep, lname);
                    editor.putString(Cardnop, crdno);
                    editor.putString(Cvvnop, cvvno);
                    editor.commit();

                    Intent i = new Intent (MainActivity.this, profile.class);
                    startActivity(i);
                }
            }
        });
    }
}

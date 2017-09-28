package com.xyzmastercrd.www;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class transaction extends AppCompatActivity {
    List<data> heroList;

    ListView listView;
    SharedPreferences sharedpreferences;
    final private int REQUEST_CODE_WIFI_PERMISSIONS = 1;
    public static final String mycardata = "mycardetail";
    public static final String Firstnamp = "firname";
    public static final String Lasnamep = "lasname";
    public static final String Cardnop = "carno";
    public static final String Cvvnop = "cvvno";
    public static final String dummymoney = "dummymoney";
    public static final String dummycolpoint = "dummycollection";
    int pointadd;
    String date;
    String to;

    MyListAdapter adapter;



    private String urlJsonObj = "https://xyzmasterc.au-syd.mybluemix.net/work.json";

    // json array response url
   // private String urlJsonArry = "https://api.androidhive.info/volley/person_array.json";

    private static String TAG = MainActivity.class.getSimpleName();
    private Button btnMakeObjectRequest, btnMakeArrayRequest;

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;


   // int amountres;
    int amount;
    TextView bal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        adapter = new MyListAdapter(this, R.layout.my_custom_list, heroList);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        makeJsonObjectRequest();


        if (Build.VERSION.SDK_INT >= 23) {
            wifiPer();
        } else {
            isInternetOn();

        }






        sharedpreferences = getSharedPreferences(mycardata, Context.MODE_PRIVATE);
        int money = sharedpreferences.getInt(dummymoney, 1);
        bal = (TextView)findViewById(R.id.balance);
        bal.setText(String.valueOf(money));

        //creating the adapter
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Transactions");
    }


    public void dialogue(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(transaction.this);
        builder1.setMessage("Click Redeem To Get your 1% CashBack");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Reedeem",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        int money = sharedpreferences.getInt(dummymoney, 1);
                        int s = pointadd + money;

                        editor.putInt(dummymoney, s);
                        bal.setText(String.valueOf(s));
                        //  Toast.makeText(transaction.this, "You got your 1% cashback", Toast.LENGTH_SHORT).show();
                        sendNotification();
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


    public void sendNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        Intent intent = new Intent(transaction.this, collection.class);
  //      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.journaldev.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Cashback");
        builder.setContentText("You Got 1% cashback on current transaction");
        builder.setSubText("Tap to view the rest points");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



    public final boolean isInternetOn() {
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            makeJsonObjectRequest();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Intent i = new Intent(transaction.this,wifierror.class);
            startActivity(i);
            return false;
        }
        return false;
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(transaction.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    public void wifiPer(){
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_NETWORK_STATE)) {
                showMessageOKCancel("Allow permission to view Transactions",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[] {Manifest.permission.ACCESS_NETWORK_STATE},
                                        REQUEST_CODE_WIFI_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                    REQUEST_CODE_WIFI_PERMISSIONS);
            return;
        }
        isInternetOn();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_WIFI_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    isInternetOn();
                } else {
                    // Permission Denied
                    Toast.makeText(transaction.this, "Wifi Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String dateres = response.getString("date");
                    String  to1 = response.getString("to1");
                    String  amount1 = response.getString("amount1");

                    String  to2 = response.getString("to2");
                    String  amount2 = response.getString("amount2");

                    String  to3 = response.getString("to3");
                    String  amount3 = response.getString("amount3");

                    String  to4 = response.getString("to4");
                    String  amount4 = response.getString("amount4");

                    String  to5 = response.getString("to5");
                    String  amount5 = response.getString("amount5");

                    String  to6 = response.getString("to6");
                    String  amount6 = response.getString("amount6");

                    heroList.add(new data( to1 , amount1, dateres));
                    heroList.add(new data( to2 , amount2, dateres));
                    heroList.add(new data( to3 , amount3, dateres));
                    heroList.add(new data( to4 , amount4, dateres));
                    heroList.add(new data( to5 , amount5, dateres));
                    heroList.add(new data( to6 , amount6, dateres));

                    //attaching adapter to the listview
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(id==0){
                                pointadd = 15;
                                dialogue();

                            }else if(id==1){
                                pointadd = 56;
                                dialogue();



                            }
                            else if(id==2){
                                pointadd = 45;
                                dialogue();


                            }
                            else if(id==3){
                                pointadd = 85;
                                dialogue();


                            }else if(id==4){
                                pointadd = 55;
                                dialogue();


                            }
                            else if(id==5){
                                pointadd = 85;
                                dialogue();


                            }



                        }
                    });





                    /*----------------------------------*/

//                    txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Connection problem", // " + e.getMessage()
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
               /* Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();*/
                Toast.makeText(getApplicationContext(),
                        "Connection problem", // " + e.getMessage()
                        Toast.LENGTH_LONG).show();
                            hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}

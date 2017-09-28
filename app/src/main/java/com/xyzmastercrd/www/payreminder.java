package com.xyzmastercrd.www;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.AlarmClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.Manifest.permission;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class payreminder extends AppCompatActivity {
    final private int REQUEST_CODE_ALARM_PERMISSIONS = 8;

    private int mHour, mMinute;
    Button set;
    TimePicker tp;
    EditText fname,amount;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Pay Reminder");

        setContentView(R.layout.activity_payreminder);

       tp = (TimePicker)findViewById(R.id.timePicker);

        fname = (EditText)findViewById(R.id.firstname);


        amount = (EditText)findViewById(R.id.amount);

        //   String strDateTime = dp.getYear() + "-" + (dp.getMonth() + 1) + "-" + dp.getDayOfMonth() + " "+ tp.getCurrentHour() + ":" + tp.getCurrentMinute();
ImageView bt = (ImageView)findViewById(R.id.setreminder);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    alarmPer();
                } else {
                    setAlarm();

                }
            }
        });



    }

    public void setAlarm(){
      /*  final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        // txtTime.setText(hourOfDay + ":" + minute);*/


        String first = fname.getText().toString();
        String amt = amount.getText().toString();
        int hour =  tp.getCurrentHour();
        int min = tp.getCurrentMinute();

                        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                                .putExtra(AlarmClock.EXTRA_MESSAGE, "Pay Rs. "+amt+" To "+first)
                                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                                .putExtra(AlarmClock.EXTRA_MINUTES, min);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }

/*
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
*/
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(payreminder.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    public void alarmPer(){
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.SET_ALARM);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.SET_ALARM)) {
                showMessageOKCancel("Allow permission to set Alarm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[] {permission.SET_ALARM},
                                        REQUEST_CODE_ALARM_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(new String[]{Manifest.permission.SET_ALARM},
                    REQUEST_CODE_ALARM_PERMISSIONS);
            return;
        }
        setAlarm();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
                   case REQUEST_CODE_ALARM_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    setAlarm();
                } else {
                    // Permission Denied
                    Toast.makeText(payreminder.this, "Alarm Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    /*-----------*/
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
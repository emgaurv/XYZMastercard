package com.xyzmastercrd.www;

import java.util.ArrayList;
import java.util.Locale;

import android.Manifest.permission;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class moresmart extends AppCompatActivity implements RecognitionListener,TextToSpeech.OnInitListener{
    private TextView inputSearch;
    private Intent intent;
    private SpeechRecognizer speech = null;
    private TextView result;
    private ImageView btnSpeak;
    private ImageView startService,stopService;
    private String inputSearcha;
    private TextToSpeech tts;
    Camera camera1;
    private int mHour, mMinute;

    final private int REQUEST_CODE_MIC_PERMISSIONS = 1;
    SharedPreferences sharedpreferences;
    public static final String Cardnop = "carno";
    public static final String mycardata = "mycardetail";
    public static final String Firstnamp = "firname";
    public static final String Lasnamep = "lasname";

    public static final String addressp = "add";
    public static final String dateofbithp = "dob";
    public static final String dummycolpoint = "dummycollection";
    public static final String dummymoney = "dummymoney";


    String cardnum;
    String fisname;
    String lasname;
    String address;
    String dateofBirth;
    int currentmoney;
    int currentpoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moresmart);
        ImageView iv = (ImageView) findViewById(R.id.commands);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedpreferences = getSharedPreferences(mycardata, Context.MODE_PRIVATE);


        cardnum = sharedpreferences.getString(Cardnop, "");
        fisname = sharedpreferences.getString(Firstnamp, "");
        lasname = sharedpreferences.getString(Lasnamep, "");
        address = sharedpreferences.getString(addressp, "");
        dateofBirth = sharedpreferences.getString(dateofbithp, "");
        currentmoney = sharedpreferences.getInt(dummymoney, 1);
        currentpoint = sharedpreferences.getInt(dummycolpoint, 1);

        getSupportActionBar().setTitle("XYZ Talk");


        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent inte = new Intent(moresmart.this, commandlist.class);
                startActivity(inte);

            }
        });
        ImageView line = (ImageView)findViewById(R.id.line);
        line.setVisibility(View.INVISIBLE);

        ImageView green = (ImageView)findViewById(R.id.green);
        green.setVisibility(View.INVISIBLE);

        if (Build.VERSION.SDK_INT >= 23) {
            requestMic();
        } else {
            promptSpeechInput();
        }

        result = (TextView) findViewById(R.id.result);

        tts = new TextToSpeech(this, this);
        inputSearch = (TextView) findViewById(R.id.inputSearch);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    /*--------MIC permissio--------*/
    private void requestMic() {
        int hasWriteContactsPermission = checkSelfPermission(permission.RECORD_AUDIO);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(permission.RECORD_AUDIO)) {
                showMessageOKCancel("You need to allow access to Mic",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[] {permission.RECORD_AUDIO},
                                        REQUEST_CODE_MIC_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(new String[]{permission.RECORD_AUDIO},
                    REQUEST_CODE_MIC_PERMISSIONS);
            return;
        }
        promptSpeechInput();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(moresmart.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    public void promptSpeechInput() {
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener((RecognitionListener) this);
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            btnSpeak = (ImageView) findViewById(R.id.btnSpeak);

            btnSpeak.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {

                            speech.startListening(intent);
                            ImageView green = (ImageView)findViewById(R.id.green);
                            green.setVisibility(View.VISIBLE);
                            ImageView line = (ImageView)findViewById(R.id.line);
                            line.setVisibility(View.VISIBLE);

                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            speech.stopListening();
                            ImageView green = (ImageView)findViewById(R.id.green);
                            green.setVisibility(View.INVISIBLE);
                            ImageView line = (ImageView)findViewById(R.id.line);
                            line.setVisibility(View.INVISIBLE);


                        }
                    }
                    return true;
                }
            });
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override

    public void onDestroy() {
        super.onDestroy();
    }
    /*-----------------startingofcommands---------------------------------*/

    /*-------------------------------------------------------*/
    public void commands(){
        /*----------pro----------*/
        String balance[] = {"my current balance","Check balance","check balance","balance","Balance"};
        String point[] = {"my current points","Check points","check points","points","point","points","Points","Point"};
        String cardno[] = {"my card number","Card number","card number"};
        String transaction[] = {"last transaction","last transaction","last transactions"};
        String expenses[] = {"expenses statistics","Expenses statistics","Statistics","statistics"};
        String details[] = {"details","Details"};
        String offices[] = {"offices","office","Offices","offices"};
        String reminder[] = {"payment reminder","Payment reminder","reminder","Reminder"};
        String h[] = {"hello","Hello"};



        inputSearcha = inputSearch.getText().toString();
        if(inputSearcha.contains(balance[0]) ||
                inputSearcha.contains(balance[1])  ||
                inputSearcha.contains(balance[2])  ||
                inputSearcha.contains(balance[3]) ||
                inputSearcha.contains(balance[4])){
            result.setText("Your Current balance is Rs.:" +String.valueOf(currentmoney));
            runspeak();
         //   set.setVisibility(View.VISIBLE);
            runspeak();
        }

        //invetion
        else if (inputSearcha.contains(point[0]) ||
                inputSearcha.contains(point[1])||
                inputSearcha.contains(point[3])||
                inputSearcha.contains(point[4])||
                inputSearcha.contains(point[5])||
                inputSearcha.contains(point[6])||
                inputSearcha.contains(point[7])) {
            result.setText("Your Current points are: "+String.valueOf(currentpoint));
            runspeak();

        }
        else if (inputSearcha.contains(h[0]) ||
                inputSearcha.contains(h[1])) {
            result.setText("Hello! I'm XYZ Talk You May Check Command List to Order me");
            runspeak();

        }


        else if (inputSearcha.contains(cardno[0]) ||
                inputSearcha.contains(cardno[1])||
                inputSearcha.contains(cardno[2])) {
            result.setText("Your card Number is: "+ cardnum);
            runspeak();

        }
        else if (inputSearcha.contains(transaction[0]) ||
                inputSearcha.contains(transaction[1]) ||
                inputSearcha.contains(transaction[2])) {
            result.setText("You did last Transaction on 15 september. The amount was Rs. 8589 ");
            runspeak();
        }
        else if (inputSearcha.contains(expenses[0]) ||
                inputSearcha.contains(expenses[1]) ||
                inputSearcha.contains(expenses[2]) ||
                inputSearcha.contains(expenses[3])) {

            result.setText("Opening Statistics");
            runspeak();
            Intent i = new Intent(moresmart.this,graph.class);
            startActivity(i);

        }

        else if (inputSearcha.contains(details[0]) ||
                inputSearcha.contains(details[1])) {

            result.setText("Howdy! " + fisname + " Your card number is: " + cardnum + " and your account number is: 595959595 and your current balance is " + currentmoney);
            runspeak();        }


        else if (inputSearcha.contains(offices[0]) ||
                inputSearcha.contains(offices[1]) ||
                inputSearcha.contains(offices[2]) ||
                inputSearcha.contains(offices[3])) {

            result.setText("Finding Offices on map");
            runspeak();
           Intent i = new Intent(moresmart.this,neaybyoffice.class);
            startActivity(i);

        }
        else if (inputSearcha.contains(reminder[0]) ||
                inputSearcha.contains(reminder[1]) ||
                inputSearcha.contains(reminder[2]) ||
                inputSearcha.contains(reminder[3])) {

            result.setText("opening Reminder");
            runspeak();
            Intent i = new Intent(moresmart.this,payreminder.class);
            startActivity(i);

        }
        else {
            result.setText("Sorry! ...");

            runspeak();
        }
    }

   /*------------------------endofcommand-----------------------------------*/

    @Override
    public void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (speech != null) {
            speech.destroy();

          /*  Log.i(LOG_TAG, "destroy");*/
        }
    }

    @Override
    public void onBeginningOfSpeech() {
        //Log.i(LOG_TAG, "onBeginningOfSpeech");
        inputSearch.setText("Listening...");
        result.setText(":. :. ..:.. .: .: ");
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        // Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        //Log.i(LOG_TAG, "onEndOfSpeech");
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        inputSearch.setText(errorMessage);
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        // Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        //  Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        //  Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        inputSearch.setText(matches.get(0));
        commands();
    }


    @Override
    public void onRmsChanged(float rmsdB) {
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Try again...";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "Recognizing...";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "No Intenrnet Connection";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "Try again...";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }
    //---------------------Text To Speech-----------
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
          /*  tts.setPitch(1.1f);*/
            tts.setSpeechRate(0.9f);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                runspeak();
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }
    private void speakOut() {
        CharSequence text = result.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"id1");
    }
    private void runspeak(){
        if(Build.VERSION.SDK_INT <=  21){
            TextView tv = (TextView)findViewById(R.id.speakerror);
            tv.setText("your device does'nt support Speech Function");

        }else{

            speakOut();
        }

    }
    /*-------runtime permission----------*/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_MIC_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    promptSpeechInput();
                } else {
                    // Permission Denied
                    Toast.makeText(moresmart.this, "Mic Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    /*-----------*/

    }

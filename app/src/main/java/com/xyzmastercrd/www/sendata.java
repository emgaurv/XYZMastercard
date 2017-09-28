package com.xyzmastercrd.www;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class sendata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendata);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Button bt = (Button) findViewById(R.id.share);
        getSupportActionBar().setTitle("Share Account Data");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Account No.: 7898784854 \n" +
                        " IFSC Code.: 5678 \n" +
                        " Bank Name.: XYZ eCommerce \n" +
                        " Branch.: Varanasi,UP");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser( sendIntent,"Send Via"));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

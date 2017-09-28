package com.xyzmastercrd.www;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("expenses statistics");
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 0),
                new DataPoint(3, 100),
                new DataPoint(4, 300),
                new DataPoint(5, 900),
                new DataPoint(6, 1200),
                new DataPoint(7, 1500),
        });
        graph.addSeries(series);

        ImageView iv = (ImageView) findViewById(R.id.weeker);
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "Sorry! No Previous Data Available", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

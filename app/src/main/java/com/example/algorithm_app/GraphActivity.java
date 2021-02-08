package com.example.algorithm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final String TAG = "Swipe Position";
    private float x1, x2;
    private static int MIN_DISTANCE = 150;
    private GestureDetector gestureDetector;
    private DB db = new DB(GraphActivity.this);

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        try{
            BarChart barChart = findViewById(R.id.barChart);
            BarDataSet barDataSet = new BarDataSet(db.getAllLevelAndDate(), "Difficulty Level");
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(16f);

            BarData barData = new BarData(barDataSet);

            barChart.setFitBars(true);
            barChart.setData(barData);
            barChart.getDescription().setText("Difficulty Level Of All Algos");
            barChart.animateY(2000);

        }
        catch (Exception e){
            Toast.makeText(GraphActivity.this, "Error populating data", Toast.LENGTH_SHORT).show();
        }

        // initialize gestureDetector
        this.gestureDetector = new GestureDetector(GraphActivity.this, this);
    }

    public void launchDB(View v){
        Intent i = new Intent(this, Database.class);
        startActivity(i);
    }

    //override on touch event
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;

            case MotionEvent.ACTION_UP:
                x2 = event.getX();

                float valueX = x2-x1;

                if(Math.abs(valueX) > MIN_DISTANCE) {
                    if(x2>x1) {
                        Toast.makeText(this, "Right is swiped", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Left is swiped", Toast.LENGTH_SHORT).show();
                    }
                }
        }


        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
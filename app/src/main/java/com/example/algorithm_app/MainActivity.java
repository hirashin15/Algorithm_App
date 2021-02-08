package com.example.algorithm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    FloatingActionButton backToHome, finish;
    EditText et_algoName;
    CalendarView cv_calendarView;
    RadioGroup radioGroup;
    RadioButton selectedRadio;
    Switch sw_completed;
    Spinner sp_topics;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pulling Views
        backToHome = findViewById(R.id.backToHome);
        finish = findViewById(R.id.finish);
        et_algoName = findViewById(R.id.algoName);
        cv_calendarView = findViewById(R.id.calendarView);
        radioGroup = findViewById(R.id.radioGroup);
        sw_completed = findViewById(R.id.completed);
        sp_topics = (Spinner)findViewById(R.id.topics);

        // Add topics to dropdown menu
        String[] topics = getResources().getStringArray(R.array.topics);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, topics);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_topics.setAdapter(adapter);


        // button listeners
        backToHome.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        launchDB(v);
                        launchGraph(v);
                    }
                }
        );

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DO NOT DELETE. THIS PULLS THE SPINNER VALUE AND THE RADIO BUTTON VALUE. VERY IMPORTANTE!!!!!!!!!!!!!!!!!!!!!!!
                String selectedVal = sp_topics.getSelectedItem().toString();
//                        sp_topics.getResources().getStringArray(R.array.topics)[sp_topics.getSelectedItemPosition()];
                int id = radioGroup.getCheckedRadioButtonId();
                selectedRadio = (RadioButton) findViewById(id);
//---------------------------------------------------------------------------------------------------------------------------------------

                AlgoModel algoModel;
//
//                algoModel = new AlgoModel(-1, cv_calendarView.getDate(), et_algoName.getText().toString(),
//                        selectedRadio.getText().toString(), selectedVal, sw_completed.isChecked());
//                Toast.makeText(MainActivity.this, algoModel.toString(), Toast.LENGTH_SHORT).show();

                try{
                    algoModel = new AlgoModel(-1, cv_calendarView.getDate(), et_algoName.getText().toString(),
                            selectedRadio.getText().toString(), selectedVal, sw_completed.isChecked());
                    Toast.makeText(MainActivity.this, algoModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error creating Algorithm", Toast.LENGTH_SHORT).show();
                    algoModel = new AlgoModel(-1, -1, "error", "error", "error", false);
                }

                DB dbHelper = new DB(MainActivity.this);
                boolean success = dbHelper.addOne(algoModel);
                Toast.makeText(MainActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
//                launchDB(v);
            }
        });
    }


//    public void launchDB(View v){
//        Intent i = new Intent(this, Database.class);
////        String t = ((EditText)findViewById(R.id.name)).getText().toString();
////        i.putExtra("username", t);
//        startActivity(i);
//    }

    public void launchGraph(View v){
        Intent i = new Intent(this, GraphActivity.class);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.topics){
            String valueFromSpinner = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
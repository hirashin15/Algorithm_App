package com.example.algorithm_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "ID";
    public static final String ALGO_TABLE = "ALGO_TABLE";
    public static final String COLUMN_ALGO_DATE = "ALGO_DATE";
    public static final String COLUMN_ALGO_NAME = "ALGO_NAME";
    public static final String COLUMN_ALGO_LEVEL = "ALGO_LEVEL";
    public static final String COLUMN_ALGO_CATEGORY = "ALGO_CATEGORY";
    public static final String COLUMN_ALGO_COMPLETED = "ALGO_COMPLETED";


    public DB(@Nullable Context context) {
        super(context, "algo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ALGO_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ALGO_DATE + " NUMERIC, "
                + COLUMN_ALGO_NAME + " TEXT, "
                + COLUMN_ALGO_LEVEL + " TEXT, "
                + COLUMN_ALGO_CATEGORY + " TEXT, "
                + COLUMN_ALGO_COMPLETED + " NUMERIC)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(AlgoModel algoModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ALGO_DATE, algoModel.getDate());
        cv.put(COLUMN_ALGO_NAME, algoModel.getName());
        cv.put(COLUMN_ALGO_LEVEL, algoModel.getLevel());
        cv.put(COLUMN_ALGO_CATEGORY, algoModel.getCategory());
        cv.put(COLUMN_ALGO_COMPLETED, algoModel.isCompleted());

        long insert = db.insert(ALGO_TABLE, null, cv);
        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteOne(AlgoModel algoModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ALGO_TABLE + " WHERE " + COLUMN_ID + " = " + algoModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            return true;
        } else return false;
    }

    public List<BarEntry> getAllLevelAndDate() {
        ArrayList<BarEntry> allLevelAndDate = new ArrayList<>();
        String queryString = "SELECT " + COLUMN_ALGO_LEVEL + ", " + COLUMN_ALGO_DATE + " FROM " + ALGO_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            Log.d("Cursor Entry:", "Entered Cursor");
            do{
                int algoID = cursor.getInt(0);
                int algoDate = cursor.getInt(1);
                String algoName = cursor.getString(2);
                String algoLevel = cursor.getString(3);
                String algoCategory = cursor.getString(4);
                Boolean algoCompleted = cursor.getInt(5) == 1 ? true : false;

                allLevelAndDate.add(new BarEntry(algoDate, Integer.parseInt(algoLevel)));
            }while(cursor.moveToNext());

        } else {
            Log.d("Cursor Entry:", "Did not enter Cursor");
        }

        cursor.close();
        db.close();
//        allLevelAndDate.add(new BarEntry(10, 5));

        return allLevelAndDate;
    }

    public List<AlgoModel> getAll(){
        List<AlgoModel> returnAll = new ArrayList<>();
        String queryString = "SELECT * FROM " + ALGO_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            do{
                int algoID = cursor.getInt(0);
                int algoDate = cursor.getInt(1);
                String algoName = cursor.getString(2);
                String algoLevel = cursor.getString(3);
                String algoCategory = cursor.getString(4);
                Boolean algoCompleted = cursor.getInt(5) == 1 ? true : false;

                AlgoModel newAlgo = new AlgoModel(algoID, algoDate, algoName, algoLevel, algoCategory, algoCompleted);
                returnAll.add(newAlgo);
            }while(cursor.moveToNext());

        } else {

        }

        cursor.close();
        db.close();
        return returnAll;
    }
}

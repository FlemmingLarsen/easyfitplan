package dk.flemminglarsen.easyfitplan.Helperclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class FoodDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "foods";
    private static final String COL0 = "ID";
    private static final String COL1 = "name";


    public FoodDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL0 + " TEXT," + COL1 + " TEXT" + ");";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        contentValues.put(COL0, food);

        Log.d(TAG, "addData: Adding " + item + "and " + food + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Get all the data from database
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "addData: query: " + data);
        return data;
    }


    //Returns only the ID that matches the name passed in
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME +
                " WHERE " + COL0 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Delete from database
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    //Check if exercise already exists on the chosen day
    public boolean checkExists(String newEntry, String foods){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL0 +  "= '" + newEntry + "' AND " + COL1 + "= '"+ foods +"' "  ;
        Cursor data = db.rawQuery(query, null);
        if(data.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }

   /* //Get mondays from database
    public Cursor getMonday(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME+ " WHERE " + COL0 + " = '" + "Monday" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Get tuesdays from database
    public Cursor getTuesday(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME+ " WHERE " + COL0 + " = '" + "Tuesday" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    //Get mondays from database
    public Cursor getWednesday(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME+ " WHERE " + COL0 + " = '" + "Wednesday" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Get tuesdays from database
    public Cursor getThursday(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME+ " WHERE " + COL0 + " = '" + "Thursday" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    //Get mondays from database
    public Cursor getFriday(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME+ " WHERE " + COL0 + " = '" + "Friday" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Get tuesdays from database
    public Cursor getSaturday(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME+ " WHERE " + COL0 + " = '" + "Saturday" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    //Get mondays from database
    public Cursor getSunday(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME+ " WHERE " + COL0 + " = '" + "Sunday" + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }*/
}
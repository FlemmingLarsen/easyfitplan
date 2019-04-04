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
    private static final String COL2 = "proteins";
    private static final String COL3 = "fats";
    private static final String COL4 = "carbohydrate";


    public FoodDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL1 + " TEXT," + COL2 + " TEXT," + COL3 + " TEXT," + COL4 + " TEXT" + ");";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String food, String proteins, String fats, String carbohydrates) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, food);
        contentValues.put(COL2, proteins);
        contentValues.put(COL3, fats);
        contentValues.put(COL4, carbohydrates);

        Log.d(TAG, "addData: Adding " + food + " to " + TABLE_NAME);

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
    public Cursor getItemID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME +
                " WHERE " + COL0 + " = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Delete food from database
    public void deleteName(int id, String food){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + food + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + food + " from database.");
        db.execSQL(query);
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
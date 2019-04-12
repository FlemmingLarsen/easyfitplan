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
    private static final String TABLE_NAME = "food";
    public static final String COL0 = "id";
    public static final String COL1 = "name";
    public static final String COL2 = "proteins";
    public static final String COL3 = "fats";
    public static final String COL4 = "carbohydrate";


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

    public boolean addData(String id, String food, String proteins, String fats, String carbohydrates) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL0, id);
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

/*    public Cursor getFood(String food){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + food + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Returns only the ID that matches the name passed in
    public Cursor getItemID(String food){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + food + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }*/

    //Delete food from database
    public void deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL0 + " = '" + id + "'";
        Log.d(TAG, "deleteItem: query: " + query);
        db.execSQL(query);
    }
}
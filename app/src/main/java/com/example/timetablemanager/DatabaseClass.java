package com.example.timetablemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseClass extends SQLiteOpenHelper {
    Context context;
    private static final String DatabaseName = "MyTasks";
    private static final int DatabaseVersion = 1;

    private static final String TableName = "mytasks";
    private static final String ColumnId = "id";
    private static final String ColumnTitle = "title";
    private static final String ColumnDescription = "description";
    private static final String ColumnDay = "day";


    public DatabaseClass( Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TableName +
                " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnTitle + " TEXT, " +
                ColumnDescription + " TEXT, " +ColumnDay +" TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    void addTasks(String title, String description ,String day) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ColumnTitle, title);
        cv.put(ColumnDescription, description);
        cv.put(ColumnDay, day);

        long resultValue = db.insert(TableName, null, cv);

        if (resultValue == -1) {
            Toast.makeText(context, "Task Not Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Task Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(String day) {
        String query = "SELECT * FROM " + TableName +" WHERE "+ColumnDay+" = '"+day+"'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    void updateTask(String title, String description, String id,String day) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ColumnTitle, title);
        cv.put(ColumnDescription, description);
        cv.put(ColumnDay, day);

        long result = database.update(TableName, cv, "id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteSingleItem(String id) {
        SQLiteDatabase database = this.getWritableDatabase();

        long result = database.delete(TableName, "id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Item Not Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }

}

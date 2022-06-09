package com.example.timetablemanager.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DB_user extends SQLiteOpenHelper {
    public static final String Db_name="app.db";

    public DB_user(Context con){
        super(con ,Db_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table User(id INTEGER primary kesy AUTOINCREMENT,name TEXT, username TEXT,email TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }

    public String insertData(String Name, String Username,String Email,String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val =  new ContentValues();
        val.put("name",Name);
        val.put("username",Username);
        val.put("email",Email);
        val.put("password",Password);
        long Re = db.insert("User",null,val);
        if(Re==-1)
            return "Error";
        else return "Sign Up";
    }

    public ArrayList<User> GetData(){
        ArrayList<User> arr = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor C = db.rawQuery("select * from User",null);
        C.moveToFirst();
        while(C.isAfterLast()==false){
            arr.add(new User(C.getInt(0),C.getString(1),C.getString(2),C.getString(3),C.getString(4)));
            C.moveToNext();
        }
        return arr;
    }

    public String Delete(String id){
        SQLiteDatabase sq = this.getWritableDatabase();
        int num =sq.delete("User","id=?",new String[]{id});
        if(num!=0)
            return "Deleted";
        else return "Not Deleted";
    }

    public String DeleteAll(){
        SQLiteDatabase sq = this.getWritableDatabase();
        int num =sq.delete("User",null,null);
        if(num!=0)
            return "Deleted";
        else return "Not Deleted";
    }

    public void Update(String id,String name,String username ,String email,String password){
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("name",name);
        val.put("id",id);
        val.put("username",username);
        val.put("email",email);
        val.put("password",password);
        sq.update("User",val,"id=?",new String[]{id});
    }

    public String Search(String Us,String Pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor C = db.rawQuery("select * from User where username='"+Us+"' and password='"+Pass+"'",null);
        if(C.getCount()>0)
            return "Found";
        else return "Not Found";
    }

}

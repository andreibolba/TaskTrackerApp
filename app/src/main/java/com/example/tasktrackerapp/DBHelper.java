package com.example.tasktrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table tasks(username TEXT primary key,title TEXT, description TEXT)");
        MyDB.execSQL("create Table users(username TEXT primary key,email TEXT, password TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
//        MyDB.execSQL("drop Table if exists users");
//        MyDB.execSQL("drop Table if exists tasks");
        onCreate(MyDB);

    }

    public Boolean insertDataUser(String username, String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1)
            return false;
        return true;
    }

    public Boolean insertTasks(SQLiteDatabase MyDB, String username, String name, String description) {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("username", username);
//        contentValues.put("name", name);
//        contentValues.put("description", description);
//        long result = MyDB.insert("tasks", null, contentValues);
//        if (result == -1)
//            return false;
//        return true;
        String ROW1 = "INSERT INTO " + "tasks" + " ("
                + "username"
                + "name"
                + "description" + ") Values (" + username + "," +name+"," +description +")";
       MyDB.execSQL(ROW1);
       return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public ArrayList<Task> getTasks(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        this.insertTasks("sghs", "trash", "take it out");
        this.insertTasks("dff", "trash", "take it out");
        this.insertTasks("ffff", "trash", "take it out");
        Cursor cursor = MyDB.rawQuery("Select * from tasks where username = ?", new String[]{username});
        System.out.println(cursor.getCount());
        System.out.println("================================================================");
        int index = 0;
        ArrayList<Task> tasks = new ArrayList<>();
        while (index < cursor.getCount()) {
            tasks.add(new Task(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
            index++;
        }

        tasks.add(new Task("asdfsaf", "trash", "marcel"));
        return tasks;
    }
}



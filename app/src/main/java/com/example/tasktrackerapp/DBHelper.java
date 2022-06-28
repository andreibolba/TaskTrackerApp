package com.example.tasktrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key,firstname TEXT, lastname TEXT, phonenumber TEXT, email TEXT, password TEXT)");
        MyDB.execSQL("create Table tasks(username TEXT,title TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists tasks");
    }

    public Boolean insertData(String username,String fname,String lname,String phonenumber,String email,String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("firstname",fname);
        contentValues.put("lastname",lname);
        contentValues.put("phoneNumber",phonenumber);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);
        if(result==-1)
            return false;
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

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount()>0)
            return true;
        return false;
    }

    public User getUser(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            User loggedUser=new User();
            loggedUser.setUsername(cursor.getString(0));
            loggedUser.setfName(cursor.getString(1));
            loggedUser.setlName(cursor.getString(2));
            loggedUser.setPhone(cursor.getString(3));
            loggedUser.setEmail(cursor.getString(4));
            loggedUser.setPass(cursor.getString(5));
            return loggedUser;
        }
        return new User();
    }

    public Boolean insertTasks(String username, String title, String description) {
          SQLiteDatabase MyDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("username", username);
//        contentValues.put("name", name);
//        contentValues.put("description", description);
//        long result = MyDB.insert("tasks", null, contentValues);
//        if (result == -1)
//            return false;
//        return true;
        String ROW1 = "INSERT INTO " + "tasks" + " ("
                + "username,"
                + "title,"
                + "description" + ") Values ('" + username + "','" + title + "','" + description + "')";
        MyDB.execSQL(ROW1);
        return true;
    }


    public ArrayList<Task> getTasks(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        try {
//            this.insertTasks("aurel", "trash", "take it out");
//            this.insertTasks("dff", "trash", "take it out");
//            this.insertTasks("ffff", "trash", "take it out");
        } catch (RuntimeException re) {
            System.out.println(re);
        }
        Cursor cursor = MyDB.rawQuery("Select * from tasks where username = (?)", new String[]{username});
        System.out.println(cursor.getCount());
        System.out.println("================================================================");
        int index = 0;
        ArrayList<Task> tasks = new ArrayList<>();
        cursor.moveToFirst();
        while (index < cursor.getCount()) {
            tasks.add(new Task(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
            index++;
        }
        return tasks;
    }
}

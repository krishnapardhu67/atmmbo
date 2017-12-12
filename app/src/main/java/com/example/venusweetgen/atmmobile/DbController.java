package com.example.venusweetgen.atmmobile;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;



public class DbController extends SQLiteOpenHelper {

    public DbController(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, "atm6.db", factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       try {
           db.execSQL("create table users(ID INTEGER PRIMARY KEY   AUTOINCREMENT,name text,uname text unique,pass text,email text unique,phone text,gender text,dob text);");
           db.execSQL("create table atm(ID INTEGER PRIMARY KEY   AUTOINCREMENT,aname text,address text,uuname text,datee text);");
           db.execSQL("create table atmnotwork(ID INTEGER PRIMARY KEY   AUTOINCREMENT,aname text,address text,uuname text,category text,datee text);");
           db.execSQL("create table message(ID INTEGER PRIMARY KEY   AUTOINCREMENT,msg text,uuname text,datee text);");
           db.execSQL("create table secsafe(ID INTEGER PRIMARY KEY   AUTOINCREMENT,bname text,timings text,location text,descr text,uuname text,datee text);");
           }catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users;");
        db.execSQL("drop table if exists atmnotwork;");
        db.execSQL("drop table if exists atm;");
        db.execSQL("drop table if exists message;");
        db.execSQL("drop table if exists secsafe;");
        onCreate(db);
    }


    public String register(String name, String uname, String pass, String email, String phone, String dob, String gender) {
        long i=0;String message="";
        try {
            ContentValues cv = new ContentValues();
            cv.put("name",name);
            cv.put("uname",uname);
            cv.put("pass",pass);
            cv.put("email",email);
            cv.put("phone",phone);
            cv.put("dob",dob);
            cv.put("gender",gender);

            i=this.getWritableDatabase().insertOrThrow("users", "", cv);
            if(i>0){
                message="Register Successful";
            }else{
                message="Register Fails";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;

    }

    public Cursor login(String uname, String pass) {
        Cursor cursor=this.getReadableDatabase().rawQuery("select * from users where uname='"+uname+"' and pass='"+pass+"'",null);
        return cursor;
    }

    public String addatm(String v_name2, String v_address2, String uuname) {
        long i=0;String message="not added";
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            ContentValues cv = new ContentValues();
            cv.put("uuname",uuname);
            cv.put("aname",v_name2);
            cv.put("address",v_address2);
            cv.put("datee",dateFormat.format(date));
            i=this.getWritableDatabase().insertOrThrow("atm", "", cv);
            if(i>0){
                message="Atm Added Successful";
            }else{
                message="Fails to Add";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    public Cursor getAtm(String uuname) {
        Cursor cursor=this.getReadableDatabase().rawQuery("select * from atm where uuname='"+uuname+"'",null);
        return cursor;
    }

    public String addnotwork(String a_name, String a_address, String c_category, String uuname) {
        long i=0;String message="not added";
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            ContentValues cv = new ContentValues();
            cv.put("uuname",uuname);
            cv.put("aname",a_name);
            cv.put("address",a_address);
            cv.put("datee",dateFormat.format(date));
            cv.put("category",c_category);
            i=this.getWritableDatabase().insertOrThrow("atmnotwork", "", cv);
            if(i>0){
                message="Out of Order ATM is Added";
            }else{
                message="Fails to Add";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    public Cursor GetNotWork(String uuname) {
        Cursor cursor=this.getReadableDatabase().rawQuery("select * from atmnotwork where category='Not Working'",null);
        return cursor;
    }

    public Cursor GetCash(String uuname) {
        Cursor cursor=this.getReadableDatabase().rawQuery("select * from atmnotwork where category='No Cash'",null);
        return cursor;
    }

    public Cursor GetUser(String uuname) {
        Cursor cursor=this.getReadableDatabase().rawQuery("select * from users where uname='"+uuname+"'",null);
        return cursor;
    }

    public String sendMsg(String msg, String uuname) {
        long i=0;String message="not added";
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            ContentValues cv = new ContentValues();
            cv.put("uuname",uuname);
            cv.put("msg",msg);
            cv.put("datee",dateFormat.format(date));

            i=this.getWritableDatabase().insertOrThrow("message", "", cv);
            if(i>0){
                message="Emergency message Sent Successfully";
            }else{
                message="Fails to Send";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    public Cursor GetEmergency() {
        Cursor cursor=this.getReadableDatabase().rawQuery("select * from message",null);
        return cursor;
    }

    public String addSecSafe(String bname, String timings, String location, String descr, String uuname) {
        long i=0;String message="not added";
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            ContentValues cv = new ContentValues();
            cv.put("bname",bname);
            cv.put("timings",timings);
            cv.put("location",location);
            cv.put("descr",descr);
            cv.put("uuname",uuname);
            cv.put("datee",dateFormat.format(date));
            i=this.getWritableDatabase().insertOrThrow("secsafe", "", cv);
            if(i>0){
                message="Security message Sent Successfully";
            }else{
                message="Fails to Add";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }


    public Cursor getSecSafe() {
        Cursor cursor=this.getReadableDatabase().rawQuery("select * from secsafe order by id desc",null);
        return cursor;
    }
}
